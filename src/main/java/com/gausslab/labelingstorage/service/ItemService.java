package com.gausslab.labelingstorage.service;

import com.gausslab.labelingstorage.domain.Item;
import com.gausslab.labelingstorage.repository.ItemRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getItemList(){
        return itemRepository.findAll();
    }

    public Item submitItem(Item toAdd){
        Item itemToSave = new Item(toAdd.getAssetNumber(), toAdd.getStartDate(), toAdd.getCheckDate(), toAdd.getUseDepartment(), toAdd.getManager(),toAdd.getImage());
        String toEncode = "gausslab.labelingstorage.item_"+itemToSave.getAssetNumber();
        generateQRCode(toEncode, 512);
        itemToSave.setQrUrl(toEncode);
        return itemRepository.save(itemToSave);
    }

    public Item deleteItem(Item deleteItem){
        itemRepository.delete(deleteItem);
        return deleteItem;
    }

    public Item editItem(Item editItem){
        if(editItem.getId() != null){
            editItem.setAssetNumber(editItem.getAssetNumber());
            editItem.setStartDate(editItem.getStartDate());
            editItem.setCheckDate(editItem.getCheckDate());
            editItem.setUseDepartment(editItem.getUseDepartment());
            editItem.setManager(editItem.getManager());
            editItem.setImage(editItem.getImage());
        }
        return itemRepository.save(editItem);
    }


    private void generateQRCode(String qrData, int size) {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = writer.encode(qrData, BarcodeFormat.QR_CODE, size, size);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (bitMatrix.get(x, y)) {
                    graphics.fillRect(x, y, 1, 1);
                }
            }
        }

    }
}
