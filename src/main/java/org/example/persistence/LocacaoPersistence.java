package org.example.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.example.model.Locacao;

public class LocacaoPersistence implements Persistence<Locacao> {

    private static final String PATH = DIRECTORY+ File.separator +"locacoes.json";
    @Override
    public void save(List<Locacao> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);
    }
    @Override
    public List<Locacao> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Locacao> locacoes = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Locacao>>() {
            }.getType();
        locacoes = gson.fromJson(json, tipoLista);

            if (locacoes == null)
                locacoes = new ArrayList<>();
        }

        return locacoes;
    }
    
}
