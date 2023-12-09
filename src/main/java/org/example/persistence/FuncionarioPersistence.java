package org.example.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Cliente;
import org.example.model.Funcionario;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioPersistence implements Persistence<Funcionario>{

    private static final String PATH = DIRECTORY+ File.separator +"funcionarios.json";
    @Override
    public void save(List<Funcionario> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);
    }
    @Override
    public List<Funcionario> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Funcionario> funcionarios = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Funcionario>>() {
            }.getType();
        funcionarios = gson.fromJson(json, tipoLista);

            if (funcionarios == null)
                funcionarios = new ArrayList<>();
        }

        return funcionarios;
    }
    
}
