package be.vdab.cultuurhuis.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

public class Voorstelling implements Comparable<Voorstelling>, Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
    private String titel;
    private String uitvoerders;
    private Date datum;
    private int genreId;
    private BigDecimal prijs;
    private int vrijePlaatsen;
    
    public Voorstelling(int id, String titel, String uitvoerders, Date datum, int genreId, BigDecimal prijs, int vrijePlaatsen)
    {
        this.id = id;
        this.titel = titel;
        this.uitvoerders = uitvoerders;
        this.datum = datum;
        this.genreId = genreId;
        this.prijs = prijs;
        this.vrijePlaatsen = vrijePlaatsen;
    }

    public int getId()
    {
        return id;
    }

    public Date getDatum()
    {
        return (Date)datum;
    }

    public int getGenreId()
    {
        return genreId;
    }

    public BigDecimal getPrijs()
    {
        return prijs;
    }

    public String getTitel()
    {
        return titel;
    }

    public String getUitvoerders()
    {
        return uitvoerders;
    }

    public int getVrijePlaatsen()
    {
        return vrijePlaatsen;
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
    public int compareTo(Voorstelling v)
    {
        return this.getId() - v.getId();
    }

    public static Comparator<Voorstelling> getDatumComparator()
    {
        return new Comparator<Voorstelling>()
        {
            public int compare(Voorstelling v1, Voorstelling v2)
            {
                return v1.getDatum().compareTo(v2.getDatum());
            }
        };
    }
}
