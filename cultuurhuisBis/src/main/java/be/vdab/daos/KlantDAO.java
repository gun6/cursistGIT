package be.vdab.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import be.vdab.entities.Klant;
import be.vdab.entities.Voorstelling;
import be.vdab.exceptions.DAOException;

public class KlantDAO extends AbstractDAO {
	private static final String KLANTGEGEVENS_SELECT = "select gebruikersnaam from klanten where gebruikersnaam = ?";
	private static final String KLANT_SELECT = "select voornaam,familienaam,straat,huisnr,postcode,gemeente,gebruikersnaam,paswoord from klanten where gebruikersnaam = ?";
	private static final String KLANT_INSERT = "insert into klanten (voornaam,familienaam,straat,huisnr,postcode,gemeente,gebruikersnaam,paswoord) values (?,?,?,?,?,?,?,?)";
	private static final String PASWOORD_SELECT = "select paswoord from klanten where gebruikersnaam = ?";
	
	
	
	public String getPaswoord(String gebruikersnaam) {
		String resultaat = "";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(PASWOORD_SELECT)){
			statement.setString(1, gebruikersnaam);
			try(ResultSet resultSet = statement.executeQuery()){
				if (resultSet.next()){
					resultaat = resultSet.getString("paswoord");
				}
			}
			return resultaat;
		}
		catch (SQLException e) {
				throw new DAOException(e);
		}
	}
	
	public boolean checkKlantgegevens(String gebruikersnaam) {
		boolean resultaat = false;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(KLANTGEGEVENS_SELECT)){
			statement.setString(1, gebruikersnaam);
			try(ResultSet resultSet = statement.executeQuery()){
				if (resultSet.next()) {
						resultaat=true;	
				}
			}
			return resultaat;
			}
		catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public void insertNew(Klant klant) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(KLANT_INSERT)){
			statement.setString(1,klant.getVoornaam());
			statement.setString(2,klant.getFamilienaam());
			statement.setString(3,klant.getStraat());
			statement.setString(4,klant.getHuisnr());
			statement.setString(5,klant.getPostcode());
			statement.setString(6,klant.getGemeente());
			statement.setString(7,klant.getGebruikersnaam());
			statement.setString(8,klant.getPaswoord());
			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public Klant getKlant(String gebruikersnaam) {
		Klant klant = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(KLANT_SELECT)){
			statement.setString(1,gebruikersnaam);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()){
					klant = new Klant(resultSet.getString("voornaam"),resultSet.getString("familienaam"),resultSet.getString("straat"),resultSet.getString("huisnr"),resultSet.getString("postcode"),resultSet.getString("gemeente"),resultSet.getString("gebruikersnaam"),resultSet.getString("paswoord"));
				}
			}
			return klant;
		}
		catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	
	
}
