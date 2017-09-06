package com.jordimontornes.megawififlasher.views.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jordimontornes.megawififlasher.R;
import com.jordimontornes.megawififlasher.domain.DirectoryContentProvider;
import com.jordimontornes.megawififlasher.views.viewholder.FileItemAdapter;
import com.jordimontornes.megawififlasher.views.viewholder.FileItemData;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FileManagerFragment extends Fragment implements FileManagerListener {

    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @BindView(R.id.filepath_view)
    TextView filePathView;

    @BindView(R.id.recycler_view)
    RecyclerView fileRecyclerView;

    FileItemAdapter fileItemAdapter;
    RecyclerView.LayoutManager layoutManager;
    FileManagerPresenter presenter;
    private FileItemData[] fileItemDataArray;

    public FileManagerFragment() {
        presenter = new FileManagerPresenter(new DirectoryContentProvider());
    }

    public static FileManagerFragment newInstance() {
        return new FileManagerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataSet();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }

    private void initDataSet() {
        fileItemDataArray = new FileItemData[1];
        fileItemDataArray[0] = new FileItemData("Empty List", "/", false);
    }

    private void updateDirectoryContents() {
        fileItemDataArray = presenter.retrieveDirectoryContents();
        fileItemAdapter.setFileItemDataArray(Arrays.asList(fileItemDataArray));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_file_manager, container, false);

        ButterKnife.bind(this, rootView);

        fileItemAdapter = new FileItemAdapter(presenter);
        filePathView.setText("/sdcard/");
        layoutManager = new LinearLayoutManager(getActivity());

        fileRecyclerView.setLayoutManager(layoutManager);
        fileRecyclerView.setAdapter(fileItemAdapter);

        int externalStoragePermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (externalStoragePermission == PackageManager.PERMISSION_GRANTED) {
            updateDirectoryContents();
        } else {
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
        }

        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateDirectoryContents();
                    fileItemAdapter.notifyDataSetChanged();
                }
                return;
            }
        }
    }

    @Override
    public void onClickCommonFile() {
        Toast.makeText(getContext(), "File is not a Sega Genesis/Megadrive Rom!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClickSegaFile() {
        new RomDialogFragment().show(getFragmentManager(), "RomDialogFragment");
    }

    @Override
    public void onClickDirectory() {
        updateDirectoryContents();
        fileItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void setDirectoryPath(String fullPath) {
        filePathView.setText(fullPath);
    }

}
