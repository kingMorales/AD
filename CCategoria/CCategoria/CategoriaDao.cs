﻿using System;

using Gtk;
using MySql.Data.MySqlClient;
using System.Data;

using Serpis.Ad;

namespace CCategoria
{
    public class CategoriaDao
    {

        public static Categoria Load(object id)
        {
            IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
            dbCommand.CommandText = "select * from categoria where id = @id";
            DbCommandHelper.AddParameter(dbCommand, "id", id);
            IDataReader dataReader = dbCommand.ExecuteReader();
            dataReader.Read();
            string nombre = (string)dataReader["nombre"];
            dataReader.Close();

            Categoria categoria = new Categoria();
            categoria.Id = Convert.ToInt64(id);
            categoria.Nombre = nombre;
            return categoria;
        }

        public static void Delete(object id) {
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "delete from categoria where id = @id";
			DbCommandHelper.AddParameter(dbCommand, "id", id);
            dbCommand.ExecuteNonQuery();
        }

        public static void Save(Categoria categoria)
        {
            if (categoria.Id == 0)
                Insert(categoria);
            
            else
                Update(categoria);
        }
        private static void Insert (Categoria categoria) {
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "insert into categoria (nombre) values (@nombre)";
            DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);

			dbCommand.ExecuteNonQuery();
        }

		private static void Update(Categoria categoria)
		{
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "update categoria set nombre=@nombre where id = @id";
            DbCommandHelper.AddParameter(dbCommand, "id", categoria.Id);
            DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);

			dbCommand.ExecuteNonQuery();
		}
    }
}