package com.project.test.controller;

import com.project.test.config.security.JwtTokenProvider;
import com.project.test.model.dto.GlobalResponse;
import com.project.test.model.entity.OrgEntity;
import com.project.test.model.entity.UserEntity;
import com.project.test.repository.OrganizationRepository;
import com.project.test.repository.UserRepository;
import com.project.test.util.BaseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(path = "/org", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationController {

    @Autowired
    private OrganizationRepository repo;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private JwtTokenProvider provider;

    @Autowired
    private BaseHelper helper;

    @GetMapping("/find")
    public ResponseEntity<?>  findUser() throws Exception {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveUser(HttpServletRequest request, @RequestBody OrgEntity param) throws Exception {
        UUID uuid = provider.getUserUuidFromJWT(helper.getJwtFromRequest(request));
        UserEntity userEntity = repoUser.findByUserUuid(uuid);
        param.setUserId(Long.valueOf(userEntity.getUserId()));
        repo.save(param);
        return ResponseEntity.ok("berhasil Save Data");
    }

    @GetMapping(path = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveUser(HttpServletRequest request, @PathVariable int id) throws Exception {
        UUID uuid = provider.getUserUuidFromJWT(helper.getJwtFromRequest(request));
        UserEntity userEntity = repoUser.findByUserUuid(uuid);
        OrgEntity orgEntity = repo.findByUserId(Long.valueOf(userEntity.getUserId()));

        if(userEntity.getRoles().equals("superadmin")){
            repo.deleteById(Long.valueOf(id));
            return ResponseEntity.ok("berhasil Delete Data");
        }else{
            if(!Objects.isNull(orgEntity) && !userEntity.getRoles().equals("superadmin")){
                repo.deleteById(Long.valueOf(id));
            }else{
                return (ResponseEntity<String>) ResponseEntity.internalServerError();
            }

            return ResponseEntity.ok("berhasil Delete Data");
        }
    }

    @PutMapping(path = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editUser(HttpServletRequest request, @RequestBody OrgEntity param, @PathVariable int id) throws Exception {
        UUID uuid = provider.getUserUuidFromJWT(helper.getJwtFromRequest(request));
        UserEntity userEntity = repoUser.findByUserUuid(uuid);
        OrgEntity orgEntity = repo.findByUserId(Long.valueOf(userEntity.getUserId()));

        if(userEntity.getRoles().equals("superadmin")){
            param.setOrgId(id);
            repo.save(param);
            return ResponseEntity.ok("berhasil Edit Data");
        }else{
            if(!Objects.isNull(orgEntity) && !userEntity.getRoles().equals("superadmin")){
                repo.deleteById(Long.valueOf(id));
            }else{
                return (ResponseEntity<String>) ResponseEntity.internalServerError();
            }

            return ResponseEntity.ok("berhasil Edit Data");
        }
    }
}
