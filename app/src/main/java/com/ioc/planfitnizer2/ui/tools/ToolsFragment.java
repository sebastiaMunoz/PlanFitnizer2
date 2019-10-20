package com.ioc.planfitnizer2.ui.tools;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ioc.planfitnizer2.MainActivity;
import com.ioc.planfitnizer2.MenuActivity;
import com.ioc.planfitnizer2.R;
import com.ioc.planfitnizer2.common.CommunicationLayer;
import com.ioc.planfitnizer2.common.Helper;

import org.json.JSONException;
import org.json.JSONObject;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

      //  String token = this.getActivity().getIntent().getStringExtra("token");
        final boolean[] res = {true};
        final boolean[] salir = {false};
        new AlertDialog.Builder(ToolsFragment.this.getContext())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                .setMessage("Are you sure?")
                .setNegativeButton(android.R.string.cancel, null)//sin listener
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        //Salir
                        salir[0] = true;

                        String token = ToolsFragment.this.getActivity().getIntent().getStringExtra("token");
                        if (Helper.logout(token, ToolsFragment.this.getContext())){
                            Intent i = new Intent(ToolsFragment.this.getContext(), MainActivity.class);
                            i.putExtra("token", token);
                            startActivity(i);
                            ToolsFragment.this.getActivity().finish();
                        }

                        toolsViewModel = ViewModelProviders.of(ToolsFragment.this).get(ToolsViewModel.class);
                        View root = inflater.inflate(R.layout.fragment_tools, container, false);
                        ToolsFragment.this.getActivity().finish();


                    }
                }).show();

        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        return root;
    }

  }