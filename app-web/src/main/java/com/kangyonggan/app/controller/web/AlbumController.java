package com.kangyonggan.app.controller.web;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Album;
import com.kangyonggan.app.model.AlbumPhoto;
import com.kangyonggan.app.service.AlbumPhotoService;
import com.kangyonggan.app.service.AlbumService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author kangyonggan
 * @since 1/10/19
 */
@Controller
@RequestMapping("album")
public class AlbumController extends BaseController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumPhotoService albumPhotoService;

    /**
     * 相册
     *
     * @param model
     * @return
     */
    @GetMapping
    public String index(Model model) {
        List<Album> albums = albumService.findAllAlbums();

        model.addAttribute("albums", albums);
        return "web/album/index";
    }

    /**
     * 相册
     *
     * @param albumId
     * @param pwd
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("{albumId:[\\d]+}")
    public String detail(@PathVariable("albumId") Long albumId,
                         @RequestParam(value = "pwd", required = false, defaultValue = "") String pwd,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         Model model) {
        Album album = albumService.findAlbumByAlbumId(albumId);
        if (album == null) {
            return "web/album/none";
        }
        if (StringUtils.isNotEmpty(album.getPassword()) && !album.getPassword().equals(pwd)) {
            return "redirect:/album?msg=err-pwd";
        }

        album.setPassword(null);

        List<AlbumPhoto> albumPhotos = albumPhotoService.findAlbumPhotos(albumId, pageNum);
        PageInfo<AlbumPhoto> page = new PageInfo<>(albumPhotos);

        model.addAttribute("page", page);
        model.addAttribute("album", album);
        return "web/album/detail";
    }

}
