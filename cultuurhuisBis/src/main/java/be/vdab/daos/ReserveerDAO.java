package be.vdab.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import be.vdab.entities.Klant;
import be.vdab.entities.Reservatie;
import be.vdab.entities.Voorstelling;
import be.vdab.exceptions.DAOException;
import be.vdab.valueobjects.Reservering;

public class ReserveerDAO extends AbstractDAO {
	private static final String PLAATSEN_UPDATE = "update voorstellingen set vrijeplaatsen = vrijeplaatsen - ? where id = ?";
	private static final String RESERVEER_INSERT = "insert into reservaties (klantid,voorstellingsid,plaatsen) values (?,?,?)";
	private static final String ID_SELECT = "select id from reservaties where klantid = ? and voorstellingsid = ? and plaatsen = ?";
	private static final String KLANT_ID_SELECT = "select id from klanten where gebruikersnaam = ?";
	private static final String RESERVERING_SELECT = "select v.id, v.datum, v.titel, v.uitvoerders, v.prijs, v.vrijeplaatsen, r.plaatsen from reservaties as r inner join voorstellingen as v on r.voorstellingsid = v.id where r.id = ?";
	
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
				return id;
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
	
	public Reservering getReserveringen(long id) {
		Reservering reservering = null;
		Voorstelling voorstelling = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(RESERVERING_SELECT)){
			statement.setLong(1,id);
			try(ResultSet resultSet = statement.executeQuery()){
				if (resultSet.next()) {
					long idVoorstelling = resultSet.getLong("v.id");
					Date date = (Date) resultSet.getDate("v.datum");
					String titel = resultSet.getString("v.titel");
					String uitvoerders = resultSet.getString("v.uitvoerders");
					BigDecimal prijs = resultSet.getBigDecimal("v.prijs");
					int vrijePlaatsen = resultSet.getInt("v.vrijeplaatsen");
					voorstelling = new Voorstelling(idVoorstelling, date, titel, uitvoerders, prijs, vrijePlaatsen);
					int aantalPlaatsen = resultSet.getInt("r.plaatsen");
					reservering = new Reservering(voorstelling, aantalPlaatsen);
				}
			}
			return reservering;
		}
		catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	

}
