package be.vdab.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.vdab.entities.Klant;
import be.vdab.entities.Reservatie;
import be.vdab.exceptions.DAOException;

public class ReserveerDAO extends AbstractDAO {
	private static final String PLAATSEN_UPDATE = "update voorstellingen set vrijeplaatsen = vrijeplaatsen - ? where id = ?";
	private static final String RESERVEER_INSERT = "insert into reservaties (klantid,voorstellingsid,plaatsen) values (?,?,?)";
	private static final String ID_SELECT = "select id from reservaties where klantid = ? and voorstellingsid = ? and plaatsen = ?";
	private static final String KLANT_ID_SELECT = "select id from klanten where gebruikersnaam = ?";
	
	
	public Long reserveer(Reservatie reservatie) {
		long id = 0L;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statementInsert = connection.prepareStatement(RESERVEER_INSERT);
				PreparedStatement statementUpdate = connection.prepareStatement(PLAATSEN_UPDATE);
				PreparedStatement statementSelect = connection.prepareStatement(ID_SELECT)){
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);
			try {
				statementInsert.setLong(1,reservatie.getKlantId());
				statementInsert.setLong(2,reservatie.getVoorstellingsId());
				statementInsert.setInt(3,reservatie.getAantalPlaatsen());
				statementInsert.executeUpdate();
				statementUpdate.setInt(1,reservatie.getAantalPlaatsen());
				statementUpdate.setLong(2,reservatie.getVoorstellingsId());
				statementUpdate.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			statementSelect.setLong(1, reservatie.getKlantId());
			statementSelect.setLong(2, reservatie.getVoorstellingsId());
			statementSelect.setInt(3, reservatie.getAantalPlaatsen());
			try (ResultSet resultSet = statementSelect.executeQuery()) {
				if (resultSet.next()) {
					id = resultSet.getLong(1);
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		catch (SQLException e) {
			throw new DAOException(e);
		}
		return id;
	}
	
	public Long getKlantId(String gebruikersnaam) {
		Long klantId = 0L;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(KLANT_ID_SELECT)){
			statement.setString(1, gebruikersnaam);
			try(ResultSet resultSet = statement.executeQuery()){
				if (resultSet.next()) {
					klantId = resultSet.getLong("id");		
				}
			}
			return klantId;
		}
		catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
