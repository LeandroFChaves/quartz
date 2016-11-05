USE [master]
GO

CREATE TABLE [dbo].[jobs](
	[nome] [varchar](200) NULL,
	[descricao] [varchar](200) NULL,
	[tempo] [varchar](50) NULL,
	[id] [int] NULL,
	[classe] [varchar](50) NULL,
	[ativa] [varchar](1) NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[jobs] ([nome], [descricao], [tempo], [id], [classe], [ativa]) VALUES (N'EnvioEmail', N'Envio de email informando férias.', N'60', 1, N'CommonsMail', N'S')
GO
INSERT [dbo].[jobs] ([nome], [descricao], [tempo], [id], [classe], [ativa]) VALUES (N'LimparInadimplentes', N'Limpa da base os alunos que estão inadimplentes a mais de 2 anos', N'7', 2, N'Tarefa', N'N')
GO