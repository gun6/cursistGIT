package be.vdab.cultuurhuis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservatieDAO extends AbstractDAO 
{
	private static final String SELECT_VRIJE_PLAATSEN_SQL = "select vrijeplaatsen from voorstellingen where id = ?";
	private static final String CREATE_RESERVATIE_SQL = "insert into reservaties (klantid, voorstellingsid, plaatsen) values(?, ?, ?)";
	private static final String VERMINDER_PLAATSEN_SQL = "update voorstellingen set vrijeplaatsen = ? where id = ?";
	
	public boolean boekReservaties(int klantId, int voorstellingsId, int plaatsen)
	{
		try (Connection connection = dataSource.getConnection())
		{
			connection.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);
			
			int vrijePlaatsen = getVrijePlaatsen(voorstellingsId);
			
			if ((vrijePlaatsen - plaatsen) < 0)
			{
				return false;
			}
			else 
			{
				try (	PreparedStatement statement1 = connection.prepareStatement(CREATE_RESERVATIE_SQL);
						PreparedStatement statement2 = connection.prepareStatement(VERMINDER_PLAATSEN_SQL)
					)
				{
					//voeg reservatie toe aan database
					statement1.setInt(1, klantId); 
					statement1.setInt(2, voorstellingsId);
					statement1.setInt(3, plaatsen);
					statement1.executeUpdate();
					
					//verminder aantal plaatsen in database
					int resultPlaatsen = vrijePlaatsen - plaatsen;
					statement2.setInt(1, resultPlaatsen);
					statement2.setInt(2, voorstellingsId);
					statement2.executeUpdate();
					
					connection.commit();
				}
				catch (SQLException ex)
				{
					connection.rollback();
				}
			}
		}
		catch (SQLException ex)
		{
			throw new DAOException("FOUT: boekReservaties/ReservatieDAO: Kan reservatie niet toevoegen in databank.", ex.getCause());
		}
		return true;
	}
	
	private int getVrijePlaatsen(int voorstellingsId)
	{
		int vrijePlaatsen = 0;
		
		try (	Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_VRIJE_PLAATSEN_SQL);
			)
		{
			statement.setInt(1, voorstellingsId);
			
			try (	ResultSet resultSet = statement.executeQuery())
			{
				if (resultSet.first())
				{
					vrijePlaatsen = resultSet.getInt(1);
				}
			}
			return vrijePlaatsen;
		}
		catch(SQLException ex)
		{
			throw new DAOException("FOUT: getVrijePlaatsen/ReservatieDAO: Vrije plaatsen niet opgehaald worden uit databank.", ex.getCause());
		}
	}
}
