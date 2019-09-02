package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.db.EventsDao;

public class Model {
	private EventsDao dao;
	private SimpleWeightedGraph<Integer, DefaultWeightedEdge> grafo;
	private List<Integer> distretti;
	
	public Model() {
		dao= new EventsDao();
		grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}

	public List<Integer> getanni() {
		return this.dao.listaanni();
	}
	
	public void creagrafo(Integer anno) {
		
		distretti=this.dao.listavertici();
		Graphs.addAllVertices(this.grafo, distretti);
		for( int i1: this.grafo.vertexSet())
			for(int i2: this.grafo.vertexSet())	{
				if(i1!=i2) {
					if(!this.grafo.containsEdge(i1, i2) && !this.grafo.containsEdge(i2, i1)) {
						Graphs.addEdge(this.grafo, i1, i2, calcolapeso(i1, i2,anno));
					}
				}
		}
	}

	private double calcolapeso(int i1, int i2, int anno) {
		int lat1=this.dao.medialat(anno, i1);
		int lat2=this.dao.medialat(anno, i2);
		
		int lon1=this.dao.medialon(anno, i1);
		int lon2=this.dao.medialon(anno, i2);

		return LatLngTool.distance(new LatLng(lat1,lon1), new LatLng(lat2,lon2),LengthUnit.KILOMETER);
	}

	public int getnvertici() {
		return this.grafo.vertexSet().size();
	}

	public int getnarchi() {
		return this.grafo.edgeSet().size();
	}
	public List<Vicini> stampagrafo(int i) {
			List <Vicini> listavic= new ArrayList<>();
			List<Integer> vicini=Graphs.neighborListOf(this.grafo, i);
			for(int v : vicini) {
				if(this.grafo.getEdgeWeight(this.grafo.getEdge(i, v))>0)
					listavic.add(new Vicini(v, (int) this.grafo.getEdgeWeight(this.grafo.getEdge(i, v))));
			}
			Collections.sort(listavic);
			return listavic;
			}

	public List<Integer> getvertici() {
		return this.distretti;
	}
	}
