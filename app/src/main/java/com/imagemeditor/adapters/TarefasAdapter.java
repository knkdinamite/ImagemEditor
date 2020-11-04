package com.projeto_padrao.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;

import java.util.List;

public class TarefasAdapter extends BaseAdapter {

    Context context;
    List<Tarefa> tarefas;
    private LayoutInflater mInflater;

    public TarefasAdapter(Context context, List<Tarefa> usuarios) {
        this.tarefas = usuarios;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Tarefa tarefa = tarefas.get(position);
        tarefa.setContext(context);
        Usuario usuarioLogado = Usuario.verificaUsuarioLogado();

        final TarefasAdapter.ListContent holder;
        View v = convertView;
        if (v == null) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(R.layout.tarefa_item_lista, null);
            holder = new TarefasAdapter.ListContent();
            holder.tarefas_lista_textview_nome = (TextView) v.findViewById(R.id.tarefas_lista_textview_nome);
            holder.tarefas_item_view = (View) v.findViewById(R.id.tarefas_item_view);
            holder.tarefas_item_delete = (ImageView) v.findViewById(R.id.tarefas_item_delete);
            holder.tarefas_item_lista_progressBar = (ProgressBar) v.findViewById(R.id.tarefas_item_lista_progressBar);
            holder.tarefas_item_lista_progressBar.setVisibility(View.GONE);

            holder.tarefas_item_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.tarefas_item_lista_progressBar.setVisibility(View.VISIBLE);
                    holder.tarefas_item_delete.setVisibility(View.GONE);

                   // tarefa.deletarTarefa();
                }
            });


            holder.tarefas_item_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Aplicacao.irParaTarefaDetalheActivity(tarefa.getContext(),tarefa.getId());
                }
            });


            v.setTag(holder);
        } else {
            holder = (TarefasAdapter.ListContent) v.getTag();
        }

        holder.tarefas_lista_textview_nome.setText(tarefa.getDescricao());

        return v;
    }

    public static class ListContent {
        TextView tarefas_lista_textview_nome;
        View tarefas_item_view;
        ImageView tarefas_item_delete;
        ProgressBar tarefas_item_lista_progressBar;
    }
}