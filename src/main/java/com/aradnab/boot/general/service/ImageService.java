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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

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
        String fileName = body.get("userId")+"_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+body.get("name");
        String imgPath = ResourceUrl.WRITABLE_PROFILE_DIR + "/"+fileName;
        writeImage(encodedImage,imgPath);
        return fileName;
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
        System.out.println(uploadDir);
        File file1 = new File(uploadDir);
        file1.createNewFile();
        FileOutputStream stream = new FileOutputStream(file1);
        stream.write(file.getBytes());
        stream.close();
    }
}

