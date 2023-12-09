package org.example.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.example.model.Filial;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FilialPersistence implements Persistence<Filial> {

    private static final String PATH = DIRECTORY+ File.separator +"filiais.json";
    @Override
    public void save(List<Filial> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);
    }
    @Override
    public List<Filial> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Filial> filiais = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Filial>>() {
            }.getType();
        filiais = gson.fromJson(json, tipoLista);

            if (filiais == null)
                filiais = new ArrayList<>();
        }

        return filiais;
    }
    
}
