package com.grafos.SmallWorld;

import com.grafos.SmallWorld.Stream.GraphGenerator;

import org.graphstream.graph.implementations.SingleGraph;

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
	}
}
