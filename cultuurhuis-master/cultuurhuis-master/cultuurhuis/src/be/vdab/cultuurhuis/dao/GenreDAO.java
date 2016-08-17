package be.vdab.cultuurhuis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO extends AbstractDAO
{	
	private static final String FIND_ALL_GENRES_SQL = "select naam from genres order by naam";
	
	public Iterable<String> findAllGenres()
	{
		try (Connection connection = dataSource.getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery(FIND_ALL_GENRES_SQL)) 
		{
			List<String> genres = new ArrayList<>();
			
			while(resultSet.next())
			{
				genres.add(resultSet.getString("naam"));
			}
			return genres;
		} 
		catch(SQLException ex) 
		{
			throw new DAOException("FOUT: findAllGenres/GenreDAO: Genres kunnen niet opgehaald worden uit de databank.", ex.getCause());
		}
	}
}
