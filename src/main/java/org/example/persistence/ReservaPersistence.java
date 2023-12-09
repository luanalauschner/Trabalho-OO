package org.example.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.example.model.Reserva;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReservaPersistence implements Persistence<Reserva>{

    private static final String PATH = DIRECTORY+ File.separator +"reservas.json";
    @Override
    public void save(List<Reserva> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);
    }
    @Override
    public List<Reserva> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Reserva> reservas = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Reserva>>() {
            }.getType();
        reservas = gson.fromJson(json, tipoLista);

            if (reservas == null)
                reservas = new ArrayList<>();
        }

        return reservas;
    }
    
}
