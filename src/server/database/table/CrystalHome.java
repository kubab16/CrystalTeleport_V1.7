package server.database.table;

import server.configuration.LocalConfiguration;

public class CrystalHome {
	private static String tableHome = "Create table if not exists " + LocalConfiguration.DBPrefix.toString() + "home (" + 
			"id_home int auto_increment primary key," + 
			"name varchar(20)," + 
			"uuid varchar(50) not null," + 
			"x double not null," + 
			"y double not null," + 
			"z double not null," + 
			"world varchar(50) )";
	private static String UserTable = "Create table if not exists " + LocalConfiguration.DBPrefix.toString() + "player (" + 
			"UUID varchar(50) not null primary key," + 
			"PremiumHome int default 0,"+
			"Home int default 0)";
	public static String getTable()
	{
		return tableHome;
	}
	public static String GetTableUser()
	{
		return UserTable;
	}
}
