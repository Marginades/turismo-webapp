package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Comprable;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import persistence.PromocionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;

public class PromocionDAOImpl implements PromocionDAO {

	public List<Promocion> findAll() {
		try {
			String sql = "SELECT * FROM PROMOCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promociones = new LinkedList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromocion(resultados));
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Promocion find(int id) {
		try {
			String sql = "SELECT * FROM PROMOCIONES WHERE ID_PROMO = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;
			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public List<Comprable> generadorDeAtracciones(String atracciones_promo) {
		
		try {
		String listaTemporal[] = atracciones_promo.split("-");
		List<Comprable> atracciones = new LinkedList<Comprable>();
		
		for (int i = 0; i < listaTemporal.length; i++) {
			
			atracciones.add(DAOFactory.getAtraccionDAO().find(Integer.valueOf(listaTemporal[i])));
			
		}
		return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promocion toPromocion(ResultSet promocionRegister) throws SQLException {
		Promocion promo = null;
		if (promocionRegister.getString(3).equals("ABS")) {
		
			promo = new PromocionAbsoluta(promocionRegister.getInt(1), promocionRegister.getString(2),
					promocionRegister.getString(3), promocionRegister.getString(4),
					promocionRegister.getString(5), promocionRegister.getDouble(6),
					promocionRegister.getString(8), promocionRegister.getBoolean(9));
		}

		if (promocionRegister.getString(3).equals("POR")) {

			promo = new PromocionPorcentual(promocionRegister.getInt(1), promocionRegister.getString(2),
					promocionRegister.getString(3), promocionRegister.getString(4),
					promocionRegister.getString(5), promocionRegister.getDouble(6),
					promocionRegister.getString(8), promocionRegister.getBoolean(9));
		}

		if (promocionRegister.getString(3).equals("AXB")) {

			promo = new PromocionAxB(promocionRegister.getInt(1), promocionRegister.getString(2),
					promocionRegister.getString(3), promocionRegister.getString(4),
					promocionRegister.getString(5), promocionRegister.getString(8),
					promocionRegister.getBoolean(9));
		}

		return promo;
	}

	@Override
	public int insert(Promocion promocion) {
		try {
			String sql = "INSERT INTO PROMOCIONES (NOMBRE, TIPO_PROMOCIONES, DESCRIPCION, TIPO_ATRACCIONES, DESCUENTO, ATRACCIONES_PROMO, ACTIVE) VALUES (?, ?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, promocion.getNombre());
			statement.setString(i++, promocion.getTipo_promociones());
			statement.setString(i++, promocion.getDescripcion());
			statement.setString(i++, promocion.getTipo_atracciones());
			if ((promocion.getTipo_promociones()).equals("ABS") || (promocion.getTipo_promociones().equals("POR"))) {
				statement.setDouble(i++, promocion.getDescuento());
			}
			else { i++; };
			statement.setString(i++, promocion.getAtraccionesPlanas());
			statement.setInt(i++, 1);
			


			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promocion promocion) {
		try {
			String sql = "UPDATE PROMOCIONES SET NOMBRE = ?, TIPO_PROMOCIONES = ?, DESCRIPCION = ?, TIPO_ATRACCIONES = ?, DESCUENTO = ?, COSTO = ?, "
					+ "ATRACCIONES_PROMO = ?, ACTIVE = ? WHERE ID_ATRACCION = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, promocion.getNombre());
			statement.setString(i++, promocion.getTipo_promociones());
			statement.setString(i++, promocion.getDescripcion());
			statement.setString(i++, promocion.getTipo_atraccion());
			statement.setDouble(i++, promocion.getDescuento());
			statement.setDouble(i++, promocion.getCosto());
			statement.setString(i++, promocion.getAtraccionesPlanas());

			if (promocion.getActive() == true) {
				statement.setString(i++, "1");
			} else {
				statement.setString(i++, "0");
			}

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Promocion promocion) {
		try {
			String sql = "DELETE FROM PROMOCIONES WHERE ID_PROMO = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM PROMOCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

}
