package controller;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import model.DAO.FornecedorDAO;
import model.Token;
import model.Fornecedor;

@Path("fornecedores")
public class FornecedoresResource {

    @Context
    private UriInfo context;

    public FornecedoresResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/consultar")
    public String consultarTodos(@HeaderParam("token") String t) throws SQLException, Exception{
        if (!new Token().VerificarToken(t)) throw new Exception("token invalido");
        Gson gson = new Gson();
        ArrayList<Fornecedor> fornecedor = FornecedorDAO.retreaveAll();
        return gson.toJson(fornecedor);
                
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/consultarid")
    public String  consultarId(@HeaderParam("token") String t, @QueryParam("id")int id) throws SQLException, Exception {
        if (!new Token().VerificarToken(t)) throw new Exception("token invalido");
        Gson gson = new Gson();
        Fornecedor f = FornecedorDAO.retreave(id);
        return gson.toJson(f);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserir")
    public int inserir(@HeaderParam("token")String t, String data) throws SQLException, Exception {
        if (!new Token().VerificarToken(t)) throw new Exception("token invalido");
        Gson gson = new Gson();
        Fornecedor f = gson.fromJson(data, Fornecedor.class);
        FornecedorDAO.create(f); 
        return 200;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deletar")
    public int deletar(@HeaderParam("token")String t, String id) throws SQLException, Exception {
        if (!new Token().VerificarToken(t)) throw new Exception("token invalido");
        Gson gson = new Gson();
        Fornecedor f = gson.fromJson(id, Fornecedor.class);
        FornecedorDAO.delete(f);
        return 200;
    }    
        
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/atualizar")
    public void alterar (@HeaderParam("token")String t,String data) throws SQLException, Exception{
        if (!new Token().VerificarToken(t)) throw new Exception("token invalido");
        Gson gson = new Gson();
        Fornecedor f = gson.fromJson(data, Fornecedor.class);
        FornecedorDAO.update(f);
    }
}
