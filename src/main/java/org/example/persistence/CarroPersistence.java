package org.example.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Carro;
import org.example.model.Cliente;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CarroPersistence implements Persistence<Carro> {
    
    private static final String PATH = DIRECTORY+ File.separator +"carros.json";
    @Override
    public void save(List<Carro> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);
    }
    @Override
    public List<Carro> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Carro> carros = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Carro>>() {
            }.getType();
        carros = gson.fromJson(json, tipoLista);

            if (carros == null)
                carros = new ArrayList<>();
        }
    }
}
