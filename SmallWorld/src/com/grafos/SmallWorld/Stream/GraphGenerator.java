package com.grafos.SmallWorld.Stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;

import org.graphstream.graph.implementations.SingleGraph;

import com.grafos.SmallWorld.Utilities.Resourcer;

public class GraphGenerator
{
	public SingleGraph Create(String graphName, String nodePath, String edgePath)
	{
		int lineCount = 0;
		String currentLine;
		String[] currentLineEdges;

		SingleGraph singleGraph = new SingleGraph(graphName);
		
		try
		{
			BufferedReader nodeFile = CreateFile(nodePath);
			
			while ((currentLine = nodeFile.readLine()) != null)
			{
				singleGraph.addNode(currentLine);
			}
			
			nodeFile.close();
			
			BufferedReader edgeFile = CreateFile(edgePath);
			
			while ((currentLine = edgeFile.readLine()) != null)
			{
				lineCount++;
				
				currentLineEdges = currentLine.split(";");
				
				singleGraph.addEdge(String.format("Edge %s", lineCount), currentLineEdges[0], currentLineEdges[1]);
			}
			
			edgeFile.close();
		}
		catch (Exception e)
		{
			// TODO: Mensagem de erro aqui!
			e.printStackTrace();
		}
		
		return singleGraph;
	}
	
	private BufferedReader CreateFile(String filePath) throws FileNotFoundException
	{
		BufferedReader currentFile = new BufferedReader
		(
			new FileReader
			(
				new File(Resourcer.GetPath(getClass(), filePath))
			)
		);
		
		return currentFile;
	}
}
