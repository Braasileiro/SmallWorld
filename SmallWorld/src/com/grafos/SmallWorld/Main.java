package com.grafos.SmallWorld;

import org.graphstream.graph.implementations.SingleGraph;

import com.grafos.SmallWorld.Stream.DepthFirstSearch;
import com.grafos.SmallWorld.Stream.GraphGenerator;



public class Main
{
	public static void main(String[] args)
	{
		GraphGenerator graphGenerator = new GraphGenerator();

		SingleGraph smallWorld = graphGenerator.Create
		(
			"SmallWorld",
			"/com/grafos/SmallWorld/Data/VerticesFacebook.txt",
			"/com/grafos/SmallWorld/Data/ArestasFacebook.txt"
		);
				
		smallWorld.display();
		
		DepthFirstSearch dfs = new DepthFirstSearch(smallWorld);
		dfs.Compute();
	}
}
