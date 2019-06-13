
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Human
 */
public class Usuario {
    private String nomeP;
    
    private double valorP;
    private String data;
    private String tipo;
    private ArrayList<Usuario> usuarios;
    
    public Usuario() {
	}

   
    public Usuario(String nomeP,double valorP,String data, String tipo){
     this.nomeP=nomeP;
     this.data=data;
     this. tipo =tipo;
     this.valorP=valorP;
     usuarios = new ArrayList<>();
     }
    
    public String getNomeP(){
    return nomeP;
    }
    public String getData(){
    return data;
      
    }
    public String getTipo(){
    return tipo;
      
    }
    public double getValorP(){
    return valorP;
      
    }
    public ArrayList<Usuario> getUsuario() {
		return usuarios;
    }
    public void setUsuario(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void addUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}


    public void setNomep(String nomeP){
     this.nomeP =nomeP;
    }
    public void setData(String data){
     this.data =data;
    }
    public void setTipo(String tipo){
     this.tipo =tipo;
    }
    public void setValorP(double valorP){
     this.valorP =valorP;
    }
    public String getRetornoS() {
        
    return    getNomeP() + " " + getData() + " " + getTipo() + " " +getValorP();
    
}
    public void incluir(Connection conn) {
		String sqlInsert = "INSERT INTO nomeP,valorP,data,  tipo) VALUES (?, ?,?, ?)";

		try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, getNomeP());
			stm.setDouble(2, getValorP());
                        stm.setString(3,getData());
                        stm.setString(4,getTipo());
			stm.execute();
			// pegar o id criado no banco ?????? Se for auto_increment nao esquecer
			String query = "SELECT LAST_INSERT_ID()";
			
			} catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}

			// incluir os pedidos
                }	
    public void excluir(Connection conn) {
		String sqlDelete = "DELETE FROM cliente WHERE id = ?";
		try (PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			// excluir primeiro dos usuarios
			for (Usuario usuario : usuarios) {
				usuario.excluir(conn);
			}
			/* depois excluir o cliente
			stm.setInt(1, getIdCliente());
			stm.execute();*/
			// anular os usuarios e os atributos
			usuarios= new ArrayList<>();
			setNomep(null);
			setValorP(0);
                        setData(null);
                        setTipo(null);
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		}
	}
    public void atualizar(Connection conn) {
		String sqlUpdate = "UPDATE usuario SET nomeP=?, valorP=? WHERE nomeP = ?";

		try (PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, getNomeP());
			stm.setDouble(2, getValorP());
                        stm.setString(3,getData());
                        stm.setString(4,getTipo());
			//stm.setInt(3, getIdCliente());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		}
	}
    public ArrayList<Usuario> loadUsuarios(Connection conn) {
		String sqlSelect = "SELECT nomeP, valorP,data,tipo FROM usuario WHERE nomeP = ?";
		ArrayList<Usuario> listaUs = new ArrayList<>();

		try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, getNomeP());
			try (ResultSet rs = stm.executeQuery();) {
				/*
				 * este outro try e' necessario pois nao da' para abrir o
				 * resultsetno anterior uma vez que antes era preciso configurar
				 * o parametrovia setInt; se nao fosse, poderia se fazer tudo no
				 * mesmo try
				 */
				while (rs.next()) {
					Usuario us = new Usuario();
					us.setNomep(rs.getString("nomeP"));
					us.setData(rs.getString("data"));
					us.setValorP(rs.getDouble("valor"));
					listaUs.add(us);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return listaUs;
	}


    

      
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

