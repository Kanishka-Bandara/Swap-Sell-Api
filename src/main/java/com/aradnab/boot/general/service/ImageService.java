package com.aradnab.boot.general.service;

import com.aradnab.boot.config.ResourceUrl;
import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.Image;
import com.aradnab.boot.db_tier.exception.ResourceNotFoundException;
import com.aradnab.boot.db_tier.repository.ImageRepository;
import com.aradnab.boot.general.service.service_controller.ImageServiceInterface;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ImageService implements ImageServiceInterface {

    @Autowired
    private ImageRepository repository;
    @Autowired
    private EntityManager em;
    @Override
    public Image create(Image image) {
        image.setSavedAt(new Date());
        image.setLastUpdatedAt(new Date());
        image.setStatus(Status.LIVE_ACTIVE_STATUS);
        return repository.save(image);
    }

    @Override
    public Image update(Image image) {
        List<Image> l = em.createQuery("from Image  x where x.id = " + image.getId() + " and x.status!=" + Status.DELETE_STATUS, Image.class).getResultList();
        if (l.size() > 0) {
            image.setLastUpdatedAt(new Date());
            return repository.save(image);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + image.getId());
        }
    }

    @Override
    public List<Image> getAll() {
        return em.createQuery("from Image  x where x.status!=" + Status.DELETE_STATUS, Image.class).getResultList();
    }

    @Override
    public Image getByID(int id) {
        List<Image> l = em.createQuery("from Image  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Image.class).getResultList();
        if (l.size() > 0) {
            return l.get(0);
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public CRUDStatus delete(int id) {
        List<Image> l = em.createQuery("from Image  x where x.id = " + id + " and x.status!=" + Status.DELETE_STATUS, Image.class).getResultList();
        if (l.size() > 0) {
            Image image = l.get(0);
            image.setLastUpdatedAt(new Date());
            image.setDeletedAt(new Date());
            image.setStatus(Status.DELETE_STATUS);
            repository.save(image);
            return CRUDStatus.DELETED;
        } else {
            throw new ResourceNotFoundException("Record Not Found with id : " + id);
        }
    }

    @Override
    public String writeProfileImage(Map<String, String> body) throws Exception {
        String encodedImage = body.get("data");
        String userId = body.get("userId");
        String fileName = userId+"_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+body.get("profilePicName");
        String writable_imgPath = ResourceUrl.WRITABLE_PROFILE_DIR + "/"+fileName;
        String viewed_imgPath =    ResourceUrl.VIRTUAL_PROFILE_DIR+"/"+fileName;
        writeImage(encodedImage,writable_imgPath);
        return viewed_imgPath;
    }

    @Override
    public List<String> writeProductImages(int productId, List<String> images){
        List<String> paths = new ArrayList<>();
        images.forEach(encodedImage -> {
            String fileName = productId+"_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".jpg";
            String writable_imgPath = ResourceUrl.WRITABLE_PRODUCT_DIR + "/"+fileName;
            String viewed_imgPath =    ResourceUrl.VIRTUAL_PRODUCT_DIR+"/"+fileName;
            try {
                writeImage(encodedImage,writable_imgPath);
                paths.add(writable_imgPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return paths;
    }

    @Override
    public void writeImage(String base64EncodedImage, String path) throws Exception {
        byte[] bytes = Base64.decodeBase64(base64EncodedImage);
        Path p = Paths.get(path);
        Files.write(p, bytes);
    }

    @Override
    public void writeImage(MultipartFile file) throws Exception {
        String uploadDir = System.getProperty("user.dir") + "\\uploads\\prof_img\\" + file.getOriginalFilename();
        File file1 = new File(uploadDir);
        file1.createNewFile();
        FileOutputStream stream = new FileOutputStream(file1);
        stream.write(file.getBytes());
        stream.close();
    }

    @Override
    public String getSendAbleProfileImageUrl(String fileName){
        return ResourceUrl.VIRTUAL_PROFILE_DIR+"/"+fileName;
    }

    @Override
    public String getSendAbleProductImageUrl(String fileName){
        return ResourceUrl.VIRTUAL_PRODUCT_DIR+"/"+fileName;
    }

    @Override
    public List<String> getSendAbleProductImageUrl(List<String> files){
        List<String> imgs = new ArrayList<>();
        files.forEach(s -> {
            imgs.add(ResourceUrl.VIRTUAL_PRODUCT_DIR+"/"+s);
        });
        return imgs;
    }
}

