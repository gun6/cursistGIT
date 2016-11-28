package be.vdab.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import be.vdab.entities.Voorstelling;
import be.vdab.exceptions.DAOException;

public class VoorstellingenDAO extends AbstractDAO {
	private static final String GENRE_SELECT = "select id, naam from genres order by naam";
	private static final String VOORSTELLINGEN_PER_GENRE_SELECT = "select id,titel, uitvoerders, datum, prijs, vrijeplaatsen from voorstellingen where genreid = ? and datum > now() order by datum";
	private static final String VOORSTELLING_SELECT = "select id,titel, uitvoerders, datum, prijs, vrijeplaatsen from voorstellingen where id = ?";
	private static final String VRIJE_PLAATSEN_SELECT = "select vrijeplaatsen from voorstellingen where id = ?";
	
	
	public Map<Long, String> getGenres() {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(GENRE_SELECT)){
			Map<Long, String> genres = new ConcurrentHashMap<>();
			while (resultSet.next()) {
				genres.put(resultSet.getLong("id"), resultSet.getString("naam"));
			}
			return genres;
		}
		catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public List<Voorstelling> getHuidigeVoorstellingenVanGenre(Long id) {
		List<Voorstelling> huidigeVoorstellingenInGenre = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(VOORSTELLINGEN_PER_GENRE_SELECT)){
			statement.setLong(1, id);
			try(ResultSet resultSet = statement.executeQuery()){
				while (resultSet.next()) {
					huidigeVoorstellingenInGenre.add(new Voorstelling(resultSet.getLong("id"),(Date)resultSet.getTimestamp("datum"),resultSet.getString("titel"),resultSet.getString("uitvoerders"),resultSet.getBigDecimal("prijs"),resultSet.getInt("vrijeplaatsen")));
				}
			}
			return huidigeVoorstellingenInGenre;
		}
		catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public Voorstelling getVoorstelling(Long id) {
		Voorstelling voorstelling = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(VOORSTELLING_SELECT)){
			statement.setLong(1, id);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()){
					voorstelling = new Voorstelling(resultSet.getLong("id"),(Date)resultSet.getTimestamp("datum"),resultSet.getString("titel"),resultSet.getString("uitvoerders"),resultSet.getBigDecimal("prijs"),resultSet.getInt("vrijeplaatsen"));
				}
			}
			return voorstelling;
		}
		catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public int getPlaatsen(Long id) {
		int beschikbarePlaatsen = 0;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(VOORSTELLING_SELECT)){
			statement.setLong(1, id);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()){
					beschikbarePlaatsen = resultSet.getInt("vrijeplaatsen");
				}
			}
			return beschikbarePlaatsen;
		}
		catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
}
