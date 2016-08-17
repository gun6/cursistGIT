package be.vdab.cultuurhuis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import be.vdab.cultuurhuis.business.MandjeItem;
import be.vdab.cultuurhuis.business.Voorstelling;

public class MandjeDAO extends AbstractDAO
{
	private static final String FIND_VOORSTELLING_SQL = 
			"select * from voorstellingen where id = ?";
	
	public Iterable<MandjeItem> toonMandje(Map<Integer, Integer> mandjeMap)
	{
		List<MandjeItem> mandjeItemsList = new ArrayList<>();
		
		// loop over elke entry uit de map mandjeMap
		// elke entry bevat een reservatie (voorstellingId, plaatsen)
		for(Map.Entry<Integer, Integer> entry : mandjeMap.entrySet())
		{
			// gekozen nummer van voorstelling
			int voorstellingId = entry.getKey();
			// gekozen aantal plaatsen
			int aantalPlaatsen = entry.getValue();
			
			try(	Connection connection = dataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(FIND_VOORSTELLING_SQL);
				)
			{
				statement.setInt(1,voorstellingId);
				
				try(ResultSet resultSet = statement.executeQuery())
				{
					if(resultSet.first())
					{
						Voorstelling voorstelling = new Voorstelling(resultSet.getInt("id"), resultSet.getString("titel"), resultSet.getString("uitvoerders"),
						resultSet.getTimestamp("datum"), resultSet.getInt("genreid"), resultSet.getBigDecimal("prijs"), resultSet.getInt("vrijeplaatsen"));
						MandjeItem mandjeItem = new MandjeItem(voorstelling, aantalPlaatsen);
						mandjeItemsList.add(mandjeItem); 	// plaats object van MandjeItem in lijst met reservaties
					}
				}
			}
			catch(SQLException ex)
			{
				throw new DAOException("FOUT: toonMandje/MandjeDAO: Kan voorstelling niet lezen uit database obv voorstellingsid", ex.getCause());
			}
		}
		return mandjeItemsList;
	}
}
