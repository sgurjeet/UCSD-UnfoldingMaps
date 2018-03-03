package lifeExpect;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;

import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.*;
public class LifeExpectancyMap extends PApplet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UnfoldingMap map;
	Map<String,Float> lifeData;
    List<Feature> countries;
    List<Marker> countryMarkers;

	public void setup(){
		size(950,650, OPENGL);
		map=new UnfoldingMap(this,200,50,700,500,new Google.GoogleMapProvider());
		map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);
	    
	    String csvPath="LifeExpectancyWorldBank.csv";
	    lifeData=getLifeData(csvPath);
	    /*
	    countries=GeoJSONReader.loadDataFromJSON(this, "data/countries.geo.json");
	    countryMarkers=MapUtils.createSimpleMarkers(countries);
	    
	    
	    map.addMarkers(countryMarkers);*/
	    shadeCountries();
	}
	private void shadeCountries(){
		for(Marker m:countryMarkers){
			String cId=m.getId();
			if(lifeData.containsKey(cId)){
				Float lifeExp=lifeData.get(cId);
				int colorLevel=(int)map(lifeExp,40,90,10,255);
				m.setColor(color(255-colorLevel,100,colorLevel));
			}
			else m.setColor(color(150,150,150));
		}
	}
	public void draw(){
		background(10);
		map.draw();
	}
	private Map<String,Float> getLifeData(String csvPath){
		Map<String,Float> lifeDataMap=new HashMap<String,Float>();
		String[] rows=loadStrings(csvPath);
		for(String row:rows){
			String[] col=row.split(",");
			if(col[5]!=null){
				//System.out.println(col[5]);
				float val=parseFloat(col[5]);
				lifeDataMap.put(col[4], val);
			}
		}
		return lifeDataMap;
	}
}
