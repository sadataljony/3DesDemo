package com.sadataljony.app.android.tripledes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sadataljony.app.android.tripledes.databinding.ActivityMainBinding;
import com.sadataljony.app.android.tripledes.utils.EncryptionDecryption;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        clickListener();
    }

    private void clickListener() {

        binding.btnEncrypt.setOnClickListener(v -> {
            try {
                binding.etEncryptText.setText(EncryptionDecryption.encryptUsing3DES(
                        binding.etPlainText.getText().toString().trim(),
                        16,
                        binding.etKey.getText().toString().trim()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            binding.etPlainText.setText("");
        });

        binding.btnDecrypt.setOnClickListener(v -> {
            try {
                binding.etPlainText.setText(EncryptionDecryption.decryptUsing3DES(
                        binding.etEncryptText.getText().toString().trim(),
                        binding.etKey.getText().toString().trim()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            binding.etEncryptText.setText("");
        });
    }
}