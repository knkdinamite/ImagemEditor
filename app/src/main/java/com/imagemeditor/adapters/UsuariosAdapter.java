package com.imagemeditor.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.imagemeditor.R;
import com.imagemeditor.models.Aplicacao;

import java.util.List;

public class UsuariosAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    // Context context;
   // List<Usuario> usuarios;
   // private LayoutInflater mInflater;

   // public UsuariosAdapter(Context context, List<Usuario> usuarios) {
       // this.usuarios = usuarios;
      //  this.context = context;
   // }

    //@Override
    //public int getCount() {
       // return usuarios.size();
    //}

   // @Override
   // public Object getItem(int position) {
     //   return position;
   // }

   // @Override
   // public long getItemId(int position) {
    //    return position;
   // }

    //@SuppressLint("InflateParams")
   // @Override
    //public View getView(int position, View convertView, ViewGroup parent) {
       // final Usuario usuario = usuarios.get(position);
        //usuario.setContext(context);
        //Usuario usuarioLogado = Usuario.verificaUsuarioLogado();
        //if (usuarioLogado != null) {
        //    usuario.setKey(usuarioLogado.getKey());
        //}

       // final UsuariosAdapter.ListContent holder;
      //  View v = convertView;
       // if (v == null) {
        //    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       //     //v = mInflater.inflate(R.layout.usuarios_item_lista, null);
        //    holder = new UsuariosAdapter.ListContent();
       //    // holder.usuarios_lista_textview_email = (TextView) v.findViewById(R.id.usuarios_lista_textview_email);
            //holder.usuarios_lista_textview_nome = (TextView) v.findViewById(R.id.usuarios_lista_textview_nome);
           // holder.usuarios_item_view = (View) v.findViewById(R.id.usuarios_item_view);
            //holder.usuarios_item_delete = (ImageView) v.findViewById(R.id.usuarios_item_delete);
           // holder.usuarios_item_lista_progressBar = (ProgressBar) v.findViewById(R.id.usuarios_item_lista_progressBar);
           // holder.usuarios_item_lista_progressBar.setVisibility(View.GONE);

       //     holder.usuarios_item_delete.setOnClickListener(new View.OnClickListener() {
        //        @Override
         //       public void onClick(View v) {
          //          holder.usuarios_item_lista_progressBar.setVisibility(View.VISIBLE);
          //          holder.usuarios_item_delete.setVisibility(View.GONE);

                   // usuario.deletarUsuario();
            //    }
         //   });


            //holder.usuarios_item_view.setOnClickListener(new View.OnClickListener() {
            //    @Override
             //   public void onClick(View v) {

                   // Aplicacao.irParaUsuarioDetalheActivity(usuario.getContext(),usuario.getId());
               // }
          //  });


           // v.setTag(holder);
      //  } else {
          //  holder = (UsuariosAdapter.ListContent) v.getTag();
       // }

       // holder.usuarios_lista_textview_email.setText(usuario.getEmail());
       // holder.usuarios_lista_textview_nome.setText(usuario.getNome());

       // return v;
   // }

   // public static class ListContent {
       // TextView usuarios_lista_textview_email,usuarios_lista_textview_nome;
      //  View usuarios_item_view;
       // ImageView usuarios_item_delete;
      //  ProgressBar usuarios_item_lista_progressBar;
    //}
//}
}