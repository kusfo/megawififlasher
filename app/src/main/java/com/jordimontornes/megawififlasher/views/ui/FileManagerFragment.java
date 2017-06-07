package com.jordimontornes.megawififlasher.views.ui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jordimontornes.megawififlasher.R;
import com.jordimontornes.megawififlasher.domain.DirectoryContentProvider;
import com.jordimontornes.megawififlasher.views.viewholder.FileItemAdapter;
import com.jordimontornes.megawififlasher.views.viewholder.FileItemData;

public class FileManagerFragment extends Fragment {

    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private OnFileManagerFragmentInteractionListener fileManagerFragmentListener;

    RecyclerView fileRecyclerView;
    FileItemAdapter fileItemAdapter;
    RecyclerView.LayoutManager layoutManager;
    DirectoryContentProvider directoryContentProvider;
    FileManagerPresenter presenter;

    public FileManagerFragment() {
        // Required empty public constructor
        directoryContentProvider = new DirectoryContentProvider();
        presenter = new FileManagerPresenter();
    }


    public static FileManagerFragment newInstance() {
        return new FileManagerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataSet();
        int externalStoragePermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if(externalStoragePermission == PackageManager.PERMISSION_GRANTED) {
            retrieveDirectoryContents();
        } else  {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
        }

    }

    private void initDataSet() {
        fileItemDataArray = new FileItemData[1];
        fileItemDataArray[0] = new FileItemData("Empty List", false);
    }

    private void retrieveDirectoryContents() {
        fileItemDataArray = directoryContentProvider.getContents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_file_manager, container, false);

        fileRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        fileItemAdapter = new FileItemAdapter(presenter);
        layoutManager = new LinearLayoutManager(getActivity());

        fileRecyclerView.setLayoutManager(layoutManager);
        fileRecyclerView.setAdapter(fileItemAdapter);
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFileManagerFragmentInteractionListener) {
            fileManagerFragmentListener = (OnFileManagerFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFileManagerFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fileManagerFragmentListener = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    retrieveDirectoryContents();
                    fileItemAdapter.notifyDataSetChanged();
                }
                return;
            }
        }
    }

    public void onButtonPressed(Uri uri) {
        if (fileManagerFragmentListener != null) {
            fileManagerFragmentListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFileManagerFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
