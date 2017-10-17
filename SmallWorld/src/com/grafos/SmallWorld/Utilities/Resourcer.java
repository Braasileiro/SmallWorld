package com.grafos.SmallWorld.Utilities;

public class Resourcer
{
	public static String GetPath(Class<?> resourceClass, String resourcePath)
	{
		return resourceClass.getResource(resourcePath).getPath();
	}
}
