/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*                                                                      SCRIPT DE CRIAÇÃO DAS TABELA DA BASE DE DADOS LIGA DE FUTEBOL                                                                                   */
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
Use [Web]

print 'Criação das tabelas com suas restrições'

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
print 'Criação da tabela estabelecimento'

create table estabelecimento( 
     cod_est    int           not null  IDENTITY(1,1),
     nom_est    varchar(40)  not null,
     constraint estabelecimento_pk primary key( cod_est ) );

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
print 'Criação da tabela Franquia'

create table franquia( 
     cod_franq    int           not null  IDENTITY(1,1),
     nom_franq    varchar(40)  not null,
     constraint franquia_pk primary key( cod_franq ) );

	 ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 print 'Criação da tabela Saldo Franquia'

create table saldoFranqSist( 
     cod_franq    int           ,
     saldo		float		    not null,
    constraint franquia_fk foreign key( cod_franq)
                                     references franquia( cod_franq));

	 ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
print 'Criação da tabela Produtos'

create table produto( 
     cod_prod    int           not null  IDENTITY(1,1),
	 cod_estab    int           not null,
     nom_prod    varchar(40)  not null,
	 valor int				not null, check(valor > 0),
     constraint prod_pk primary key( cod_prod ) );


----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
print 'Criação da tabela usuario'

create table usuario( 
     nom_usu	varchar(40)           not null,
	 cod_franq	int				not null ,
     cod_usu	int				not null  IDENTITY(1,1),
	 cod_pai	int						,
	 micro_franq bit			not null,
	 raiz		int						,
	 dt_mf smalldatetime				,
	 dt_cad smalldatetime		not null,
     constraint usuario_pk primary key( cod_usu ),
     constraint usuario_fk_usuario foreign key( cod_pai )
                                     references usuario( cod_usu ));
     
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
print 'Criação da tabela pedido'

create table pedido( 
     cod_ped   int   not null  IDENTITY(1,1),
     valor	   int   not null  check( valor>0 ),
     usuario   int   not null  check( usuario>0 ),
     constraint pedido_pk primary key( cod_ped ),
     constraint pedido_fk_usuario foreign key( usuario )
                                       references usuario( cod_usu ));

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

print 'Criação da tabela cashback'

create table cashback( 
     cod_cash   int			  not null  IDENTITY(1,1),
     pedido		int			  not null, check (pedido > 0),
     usu_bene   int           not null  check( usu_bene > 0 ),
     valor      float		  not null  check( valor > 0 ),
     constraint cashback_pk primary key( cod_cash ),
    constraint cashback_fk_usuario foreign key( usu_bene )
                                     references usuario( cod_usu ),
	constraint cashback_fk_pedido foreign key( pedido )
                                     references pedido( cod_ped ));

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
print 'Criação da tabela carrinho'

create table carrinho( 
     cod_carrinho   int			  not null  IDENTITY(1,1),
	 cod_prod	    int			  not null  check( cod_prod>0 ),
     cod_ped			int			  not null, check (cod_ped > 0),
     valor_un		int           not null  check( valor_un > 0 ),
     qtd      smallint	  not null  check( qtd > 0 ),
     constraint carrinho_pk primary key( cod_carrinho),
    constraint carrinho_fk_pedido foreign key( cod_ped )
                                     references pedido( cod_ped ),
	constraint carrinho_fk_prod foreign key( cod_prod )
                                     references produto( cod_prod ));

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
--/*                                                                      SCRIPT DE CRIAÇÃO DOS ÍNDICES DAS TABELA DA BASE DE DADOS LIGA DE FUTEBOL                                                                                                        */
--/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

--print 'Criação dos índices para as tabelas'

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--print 'Criação dos índices para a tabela patrocinadores'

--create index patrocinadores_idx_nome on patrocinadores( nom_pat );

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--print 'Criação dos índices para a tabela times'

--create index times_idx_nome on times( nom_time );

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--print 'Criação dos índices para a tabela de patrcinios'

--create index patrocinios_idx_cod_pat on patrocinios( cod_pat );

--create index patrocinios_idx_cod_time on patrocinios( cod_time );

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--print 'Criação dos índices para a tabela de campeonatos'

--create index campeonatos_idx_dsc_camp on campeonatos( dsc_camp );

--create index campeonatos_idx_ano on campeonatos( ano );

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--print 'Criação dos índices para a tabela de participacoes'

--create index participacoes_idx_cod_time on participacoes( cod_time );

--create index participacoes_idx_ano on participacoes( cod_camp );

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--print 'Criação dos índices para a tabela de jogadores'

--create index jogadores_idx_cod_time on jogadores( cod_time );

--create index jogadores_idx_nom_jog on jogadores( nom_jog );

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--print 'Criação dos índices para a tabela de jogadores'

--create index historicos_idx_cod_time on historicos( cod_time );

--create index historicos_idx_cod_jog on historicos( cod_jog );

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--print 'Criação dos índices para a tabela de jogos'

--create index jogos_idx_cod_time1 on jogos( cod_time1 );

--create index jogos_idx_cod_time2 on jogos( cod_time2 );

--create index campeonatos_idx_cod_camp on jogos( cod_camp );

--create index campeonatos_idx_data on jogos( cod_camp, data );
