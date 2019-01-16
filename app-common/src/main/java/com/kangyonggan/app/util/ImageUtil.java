package com.kangyonggan.app.util;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;

/**
 * @author kangyonggan
 * @since 1/16/19
 */
public final class ImageUtil {

    private ImageUtil() {
    }

    public static void main(String[] args) throws Exception {
        File jpegFile = new File("/Users/kyg/Downloads/IMG_6322.JPG");
        Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
        for (Directory directory : metadata.getDirectories()) {
            System.out.println("--------------------------");
            for (Tag tag : directory.getTags()) {
                System.out.print("name : " + tag.getTagName() + "  -->");
                System.out.println("desc : " + tag.getDescription());
            }
        }
    }
}
