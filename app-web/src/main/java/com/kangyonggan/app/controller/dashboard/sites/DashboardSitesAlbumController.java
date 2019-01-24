package com.kangyonggan.app.controller.dashboard.sites;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Album;
import com.kangyonggan.app.model.AlbumPhoto;
import com.kangyonggan.app.service.AlbumPhotoService;
import com.kangyonggan.app.service.AlbumService;
import com.kangyonggan.app.util.FileHelper;
import com.kangyonggan.app.util.FileUpload;
import com.kangyonggan.app.util.Images;
import com.kangyonggan.common.Page;
import com.kangyonggan.common.Params;
import com.kangyonggan.common.Response;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author kangyonggan
 * @since 1/4/19
 */
@Controller
@RequestMapping("dashboard/sites/album")
public class DashboardSitesAlbumController extends BaseController {

    private static final String PATH_ROOT = "dashboard/sites/album";

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumPhotoService albumPhotoService;

    @Autowired
    private FileHelper fileHelper;

    /**
     * 相册列表界面
     *
     * @return
     */
    @GetMapping
    @PermissionMenu("SITES_ALBUM")
    public String index() {
        return PATH_ROOT + "/list";
    }

    /**
     * 相册列表查询
     *
     * @return
     */
    @GetMapping("list")
    @PermissionMenu("SITES_ALBUM")
    @ResponseBody
    public Page<Album> list() {
        return new Page<>(albumService.searchAlbums(getRequestParams()));
    }

    /**
     * 添加相册
     *
     * @param model
     * @return
     */
    @GetMapping("create")
    @PermissionMenu("SITES_ALBUM")
    @Token(key = "createAlbum")
    public String create(Model model) {
        model.addAttribute("album", new Album());
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 保存相册
     *
     * @param album
     * @param file
     * @return
     * @throws FileUploadException
     */
    @PostMapping("save")
    @ResponseBody
    @PermissionMenu("SITES_ALBUM")
    @Token(key = "createAlbum", type = Token.Type.CHECK)
    public Response save(Album album, @RequestParam(value = "file", required = false) MultipartFile file) throws FileUploadException {
        if (file != null && !file.isEmpty()) {
            String cover = fileHelper.genFileName("album");
            FileUpload.upload(fileHelper.getFileUploadPath() + "album/", cover, file);
            album.setCover("upload/album/" + cover + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
        }

        album.setUserId(currentUserId());
        albumService.saveAlbum(album);
        return Response.getSuccessResponse();
    }

    /**
     * 编辑相册
     *
     * @param albumId
     * @param model
     * @return
     */
    @GetMapping("{albumId:[\\d]+}/edit")
    @PermissionMenu("SITES_ALBUM")
    @Token(key = "editAlbum")
    public String edit(@PathVariable("albumId") Long albumId, Model model) {
        model.addAttribute("album", albumService.getAlbum(albumId));
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 更新相册
     *
     * @param album
     * @param file
     * @return
     * @throws FileUploadException
     */
    @PostMapping("update")
    @ResponseBody
    @PermissionMenu("SITES_ALBUM")
    @Token(key = "editAlbum", type = Token.Type.CHECK)
    public Response update(Album album, @RequestParam(value = "file", required = false) MultipartFile file) throws FileUploadException {
        if (file != null && !file.isEmpty()) {
            String cover = fileHelper.genFileName("album");
            FileUpload.upload(fileHelper.getFileUploadPath() + "album/", cover, file);
            album.setCover("upload/album/" + cover + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
        }
        albumService.updateAlbum(album);
        return Response.getSuccessResponse();
    }

    /**
     * 批量删除相册
     *
     * @param albumIds 相册ID
     * @return 响应
     */
    @GetMapping("delete")
    @PermissionMenu("SITES_ALBUM")
    @ResponseBody
    public Response delete(@RequestParam("albumIds") String albumIds) {
        albumService.deleteAlbums(albumIds);
        return Response.getSuccessResponse();
    }

    /**
     * 批量恢复相册
     *
     * @param albumIds 相册ID
     * @return 响应
     */
    @GetMapping("restore")
    @PermissionMenu("SITES_ALBUM")
    @ResponseBody
    public Response restore(@RequestParam("albumIds") String albumIds) {
        albumService.restoreAlbums(albumIds);
        return Response.getSuccessResponse();
    }

    /**
     * 查看照片
     *
     * @param albumId
     * @param model
     * @return
     */
    @GetMapping("{albumId:[\\d]+}")
    @PermissionMenu("SITES_ALBUM")
    public String photos(@PathVariable("albumId") Long albumId, Model model) {
        model.addAttribute("albumId", albumId);
        return PATH_ROOT + "/photos";
    }

    /**
     * 照片列表查询
     *
     * @param albumId
     * @return
     */
    @GetMapping("{albumId:[\\d]+}/list")
    @PermissionMenu("SITES_ALBUM")
    @ResponseBody
    public Page<AlbumPhoto> photoList(@PathVariable("albumId") Long albumId) {
        Params params = getRequestParams();
        params.getQuery().put("albumId", albumId);
        return new Page<>(albumPhotoService.searchAlbumPhotos(params));
    }

    /**
     * 添加照片
     *
     * @param albumId
     * @param model
     * @return
     */
    @GetMapping("{albumId:[\\d]+}/create")
    @PermissionMenu("SITES_ALBUM")
    @Token(key = "createAlbumPhoto")
    public String createPhoto(@PathVariable("albumId") Long albumId, Model model) {

        model.addAttribute("albumId", albumId);
        model.addAttribute("albumPhoto", new AlbumPhoto());
        return PATH_ROOT + "/photo-modal";
    }

    /**
     * 保存照片
     *
     * @param albumId
     * @param albumPhoto
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("{albumId:[\\d]+}/save")
    @ResponseBody
    @PermissionMenu("SITES_ALBUM")
    @Token(key = "createAlbumPhoto", type = Token.Type.CHECK)
    public Response savePhoto(@PathVariable("albumId") Long albumId, AlbumPhoto albumPhoto,
                              @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        upload(albumPhoto, file);

        albumPhoto.setAlbumId(albumId);
        albumPhotoService.saveAlbumPhoto(albumPhoto);
        return Response.getSuccessResponse();
    }

    /**
     * 编辑照片
     *
     * @param photoId
     * @param model
     * @return
     */
    @GetMapping("{albumId:[\\d]+}/{photoId:[\\d]+}/edit")
    @PermissionMenu("SITES_ALBUM")
    @Token(key = "editAlbumPhoto")
    public String editPhoto(@PathVariable("albumId") Long albumId,
                            @PathVariable("photoId") Long photoId, Model model) {

        model.addAttribute("albumId", albumId);
        model.addAttribute("albumPhoto", albumPhotoService.getAlbumPhoto(photoId));
        return PATH_ROOT + "/photo-modal";
    }

    /**
     * 更新照片
     *
     * @param albumPhoto
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("{albumId:[\\d]+}/update")
    @ResponseBody
    @PermissionMenu("SITES_ALBUM")
    @Token(key = "editAlbumPhoto", type = Token.Type.CHECK)
    public Response updatePhoto(AlbumPhoto albumPhoto, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        upload(albumPhoto, file);
        albumPhotoService.updateAlbumPhoto(albumPhoto);
        return Response.getSuccessResponse();
    }

    /**
     * 批量删除照片
     *
     * @param photoIds 照片ID
     * @return 响应
     */
    @GetMapping("{albumId:[\\d]+}/delete")
    @PermissionMenu("SITES_ALBUM")
    @ResponseBody
    public Response deletePhoto(@PathVariable("albumId") Long albumId, @RequestParam("photoIds") String photoIds) {
        albumPhotoService.deleteAlbumPhotos(photoIds);
        // 更新照片个数
        albumService.updateSize(albumId);
        return Response.getSuccessResponse();
    }

    /**
     * 批量恢复照片
     *
     * @param photoIds 照片ID
     * @return 响应
     */
    @GetMapping("{albumId:[\\d]+}/restore")
    @PermissionMenu("SITES_ALBUM")
    @ResponseBody
    public Response restorePhoto(@PathVariable("albumId") Long albumId, @RequestParam("photoIds") String photoIds) {
        albumPhotoService.restoreAlbumPhotos(photoIds);
        // 更新照片个数
        albumService.updateSize(albumId);
        return Response.getSuccessResponse();
    }

    /**
     * 文件上传
     *
     * @param albumPhoto
     * @param file
     * @throws Exception
     */
    private void upload(AlbumPhoto albumPhoto, MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return;
        }
        String photo = fileHelper.genFileName("photo");
        FileUpload.upload(fileHelper.getFileUploadPath() + "photo/", photo, file);
        String fileName = photo + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String thumbName = photo + "_THUMB." + FilenameUtils.getExtension(file.getOriginalFilename());
        albumPhoto.setUrl("upload/photo/" + fileName);

        Images.thumb(fileHelper.getFileUploadPath() + "photo/" + fileName,
                fileHelper.getFileUploadPath() + "photo/thumb/" + thumbName, 195, 133);
        albumPhoto.setThumb("upload/photo/thumb/" + thumbName);
    }
}
