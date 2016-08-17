package be.vdab.cultuurhuis.business;

/* Git Test */

import java.util.Comparator;

public class Genre implements Comparable<Genre>
{
	private int id;
    private String naam;
    
    public Genre(int id, String naam)
    {
    	this.id = id;
    	this.naam = naam;
    }

	public int getId() 
	{
		return id;
	}

	public String getNaam() 
	{
		return naam;
	}
	
	@Override
    public boolean equals(Object obj)
    {
        if(obj == null || obj.getClass() != Genre.class)
        {
            return false;
        }
        else
        {
        	return this.getId() == ((Genre)obj).getId();
        }
    }
	
	@Override
    public int hashCode()
    {
        return this.getId();
    }
	
	@Override
	public int compareTo(Genre g) 
	{
		return this.getId() - g.getId();
	}
	
	public static Comparator<Genre> getNaamComparator()
    {
        return new Comparator<Genre>()
        {
            public int compare(Genre g1, Genre g2)
            {
                return g1.getNaam().compareTo(g2.getNaam());
            }
        };
    }
}
