if exists (select name from master.dbo.sysdatabases where name = N'Web')
	drop database [Web]
go

-- CRIANDO O BANCO WEB COM O FILEGROUP MYDATA COMO PADRAO
create database [Web]	
	on (name = N'Web_Data'
		, filename = N'C:\BSI\5º Período\WebI\db\Web_Data.MDF' 
		, size = 10
		, filegrowth = 10%),
	FILEGROUP myData
	  ( NAME = 'myData_Dat1',
		FILENAME =
		   'C:\BSI\5º Período\WebI\db\myData_Dat1.ndf',
		SIZE = 10MB,
		FILEGROWTH=2MB)
	log	
		on (name = N'Web_Log'	
		, filename = N'C:\BSI\5º Período\WebI\db\Web_Log.LDF'  
		, size = 01
		, filegrowth = 10%)

GO
ALTER DATABASE Web 
  MODIFY FILEGROUP myData DEFAULT;
