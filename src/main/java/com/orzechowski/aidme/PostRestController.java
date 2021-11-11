package com.orzechowski.aidme;

import com.orzechowski.aidme.entities.helper.Helper;
import com.orzechowski.aidme.entities.helper.HelperFullMapper;
import com.orzechowski.aidme.entities.instructionset.InstructionSet;
import com.orzechowski.aidme.entities.keyword.Keyword;
import com.orzechowski.aidme.entities.keyword.KeywordRowMapper;
import com.orzechowski.aidme.entities.tag.Tag;
import com.orzechowski.aidme.entities.tag.TagRowMapper;
import com.orzechowski.aidme.entities.tutorial.Tutorial;
import com.orzechowski.aidme.entities.tutorial.TutorialRowMapper;
import com.orzechowski.aidme.entities.tutoriallink.TutorialLink;
import com.orzechowski.aidme.entities.tutorialtag.TutorialTag;
import com.orzechowski.aidme.entities.version.Version;
import com.orzechowski.aidme.entities.versioninstruction.VersionInstruction;
import com.orzechowski.aidme.entities.versionmultimedia.VersionMultimedia;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.WritableResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

@RestController
public class PostRestController
{
    @Bean
    public MultipartResolver multipartResolver()
    {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer()
    {
        return (tomcat) -> tomcat.addConnectorCustomizers((connector) -> {
            if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol) {
                AbstractHttp11Protocol<?> protocolHandler = (AbstractHttp11Protocol<?>) connector.getProtocolHandler();
                protocolHandler.setDisableUploadTimeout(true);
                protocolHandler.setConnectionUploadTimeout(60000);
            }
        });
    }

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private final ApplicationContext context;
    private final static String pathBase = "gs://aidme/";
    private final Decoder decoder = new Decoder();

    public PostRestController(JdbcTemplate jdbcTemplate, ApplicationContext context)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.context = context;
    }

    @PostMapping(path = "/create/tutorial/{email}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tutorial> createTutorial(@PathVariable String email, @RequestBody Tutorial tutorial)
    {
        Helper helper = jdbcTemplate.queryForObject("SELECT * FROM helper WHERE helper_email = " +
                        decoder.decodeEmail(email), new HelperFullMapper());
        if(helper!=null) {
            try {
                jdbcTemplate.execute("INSERT INTO tutorial VALUES(default, '" + tutorial.getTutorialName() +
                        "', " + helper.getHelperId() + ", '" + tutorial.getMiniatureString() + "', 0, 'f';");
                return ResponseEntity.ok(queryUsersTutorial(tutorial));
            } catch (DataAccessException e) {
                return null;
            }
        } else return null;
    }

    @PostMapping(path = "/create/instructions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createInstructions(@RequestBody List<InstructionSet> instructions)
    {
        if(instructions!=null && !instructions.isEmpty()) {
            try {
                for(InstructionSet instruction : instructions) {
                    jdbcTemplate.execute("INSERT INTO instruction_set VALUES(default, '" + instruction.getTitle() +
                            "', '" + instruction.getInstructions() + "', " + instruction.getTime() + ", " +
                            instruction.getTutorialId() + ", " + instruction.getPosition() + ", '" +
                            instruction.getNarrationFile() + "')");
                }
                return ResponseEntity.ok(true);
            } catch (DataAccessException e) {
                return null;
            }
        } else return null;
    }

    @PostMapping(path = "/create/versions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createVersions(@RequestBody List<Version> versions)
    {
        if(versions!=null && !versions.isEmpty()) {
            try {
                for(Version version : versions) {
                    jdbcTemplate.execute("INSERT INTO version VALUES(default, '" + version.getText() + "', " +
                            version.getTutorialId() + ", '" + version.isDelayGlobalSound() + "', '" +
                            version.isHasChildren() + "', '" + version.isHasParent() + "', " +
                            version.getParentVersionId() + ")");
                }
                return ResponseEntity.ok(true);
            } catch (DataAccessException e) {
                return null;
            }
        } else return null;
    }

    @PostMapping(path = "/create/versioninstructions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createVersionInstructions(@RequestBody List<VersionInstruction> versionInstructions)
    {
        if(versionInstructions!=null && !versionInstructions.isEmpty()) {
            try {
                for(VersionInstruction versionInstruction : versionInstructions) {
                    jdbcTemplate.execute("INSERT INTO version_instruction VALUES(default, " +
                            versionInstruction.getVersionId() + ", " + versionInstruction.getInstructionPosition() +
                            ")");
                }
                return ResponseEntity.ok(true);
            } catch (DataAccessException e) {
                return null;
            }
        } else return null;
    }

    @PostMapping(path = "/create/tutoriallinks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createTutorialLinks(@RequestBody List<TutorialLink> tutorialLinks)
    {
        if(tutorialLinks!=null && !tutorialLinks.isEmpty()) {
            try {
                for(TutorialLink tutorialLink : tutorialLinks) {
                    jdbcTemplate.execute("INSERT INTO tutorial_link VALUES(default, " +
                            tutorialLink.getTutorialId() + ", " + tutorialLink.getOriginId() + ", " +
                            tutorialLink.getInstructionNumber() + ")");
                }
                return ResponseEntity.ok(true);
            } catch (DataAccessException e) {
                return null;
            }
        } else return null;
    }

    @PostMapping(path = "/create/tutorialtags/{name}/{level}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createTutorialTags(@RequestBody List<TutorialTag> tutorialTags,
                                                      @PathVariable String name, @PathVariable int level)
    {
        if(tutorialTags!=null && !tutorialTags.isEmpty()) {
            try {
                jdbcTemplate.execute("INSERT INTO tag VALUES(default, " + name + ", " + level + ")");
                Tag tag = jdbcTemplate.queryForObject("SELECT * FROM tag WHERE tag_name = '" + name + "'",
                        new TagRowMapper());
                if(tag!=null) {
                    for(TutorialTag tutorialTag : tutorialTags) {
                        jdbcTemplate.execute("INSERT INTO tutorial_tag VALUES(default, " +
                                tutorialTag.getTutorialId() + ", " + tutorialTag.getTagId() + ")");
                    }
                    jdbcTemplate.execute("INSERT INTO tutorial_tag VALUES(default, " + tutorialTags.get(0)
                            .getTutorialId() + ", " + tag.getTagId() + ")");
                    return ResponseEntity.ok(true);
                } else return null;
            } catch (DataAccessException e) {
                return null;
            }
        } else return null;
    }

    @PostMapping(path = "/create/versionmultimedia", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createVersionMultimedia(@RequestBody List<VersionMultimedia> versionMultimedia)
    {
        if(versionMultimedia!=null && !versionMultimedia.isEmpty()) {
            try {
                for(VersionMultimedia vM: versionMultimedia) {
                    jdbcTemplate.execute("INSERT INTO version_multimedia VALUES(default, " + vM.getMultimediaId() +
                            ", " + vM.getVersionId() + ")");
                }
                return ResponseEntity.ok(true);
            } catch (DataAccessException e) {
                return null;
            }
        } else return null;
    }

    @PostMapping(path = "/create/keywords/{uniqueId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createKeywords(@RequestBody List<Keyword> keywords, @PathVariable Long uniqueId)
    {
        if(keywords!=null && !keywords.isEmpty()) {
            try {
                for(Keyword keyword: keywords) {
                    String word = keyword.getKeyword();
                    jdbcTemplate.execute("INSERT INTO keyword VALUES(default, '" + word + "')");
                    Keyword insert = jdbcTemplate.queryForObject("SELECT * FROM keyword WHERE keyword = " + word,
                            new KeywordRowMapper());
                    if (insert != null) {
                        jdbcTemplate.execute("INSERT INTO tag_keyword VALUES(default, " + uniqueId + ", " +
                                insert.getKeywordId() + ")");
                    }
                }
                return ResponseEntity.ok(true);
            } catch (DataAccessException e) {
                return null;
            }
        } else return null;
    }

    @PostMapping(path = "/upload/image/{name}/{extension}", headers = "content-type=multipart/*")
    public ResponseEntity<Boolean> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable String name,
                                               @PathVariable String extension)
    {
        String path = pathBase + "images/" + name + "." + extension;
        WritableResource newResource = (WritableResource) context.getResource(path);
        try {
            OutputStream output = newResource.getOutputStream();
            InputStream input = file.getInputStream();
            output.write(input.read());
            input.close();
            output.close();
            return ResponseEntity.ok(true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(path = "/upload/narration/{name}/{extension}", headers = "content-type=multipart/*")
    public ResponseEntity<Boolean> uploadNarration(@RequestParam("file") MultipartFile file, @PathVariable String name,
                                                   @PathVariable String extension)
    {
        String path = pathBase + "narrations/" + name + "." + extension;
        WritableResource newResource = (WritableResource) context.getResource(path);
        try {
            OutputStream output = newResource.getOutputStream();
            InputStream input = file.getInputStream();
            output.write(input.read());
            input.close();
            output.close();
            return ResponseEntity.ok(true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(path = "/upload/video/{name}/{extension}", headers = "content-type=multipart/*")
    public ResponseEntity<Boolean> uploadVideo(@RequestParam MultipartFile file, @PathVariable String name,
                                               @PathVariable String extension)
    {
        String path = pathBase + "vids/" + name + "." + extension;
        WritableResource newResource = (WritableResource) context.getResource(path);
        try {
            OutputStream output = newResource.getOutputStream();
            InputStream input = file.getInputStream();
            output.write(input.read());
            input.close();
            output.close();
            return ResponseEntity.ok(true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(path = "/upload/sound/{name}/{extension}", headers = "content-type=multipart/*")
    public ResponseEntity<Boolean> uploadSound(@RequestParam MultipartFile file, @PathVariable String name,
                                               @PathVariable String extension)
    {
        String path = pathBase + "sounds/" + name + "." + extension;
        WritableResource newResource = (WritableResource) context.getResource(path);
        try {
            OutputStream output = newResource.getOutputStream();
            InputStream input = file.getInputStream();
            output.write(input.read());
            input.close();
            output.close();
            return ResponseEntity.ok(true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //email pozostaje zakodowany aby uniknac podwojnej kropki
    @PostMapping(path = "/userdocumentuploadimage/{email}/{extension}", headers = "content-type=multipart/*")
    public ResponseEntity<Boolean> uploadDocument(@RequestParam MultipartFile file, @PathVariable String email,
                                                  @PathVariable String extension)
    {
        String path = pathBase + "docs/" + email + "." + extension;
        WritableResource newResource = (WritableResource) context.getResource(path);
        try {
            OutputStream output = newResource.getOutputStream();
            InputStream input = file.getInputStream();
            output.write(input.read());
            input.close();
            output.close();
            return ResponseEntity.ok(true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(path = "/setfullhelperdetailforemail/{email}/{id}/{name}/{surname}/{title}/{profession}/{phone}")
    public ResponseEntity<Boolean> uploadHelperDetail(@PathVariable String email, @PathVariable String id,
                                                      @PathVariable String name, @PathVariable String surname,
                                                      @PathVariable String title, @PathVariable String profession,
                                                      @PathVariable String phone)
    {
        String query = "UPDATE helper SET helper_name = '" + name + "', helper_surname = '" + surname + "'";
        if(!Objects.equals(title, "null")) {
            query = query + ", helper_title = '" + title + "'";
        }
        if(!Objects.equals(profession, "null")) {
            query = query + ", helper_profession = '" + profession + "'";
        }
        if(!Objects.equals(phone, "null")) {
            query = query + ", helper_phone = '" + phone + "'";
        }
        try {
            jdbcTemplate.execute(query + " WHERE helper_id = " + id + " AND helper_email = '" + email + "'");
            return ResponseEntity.ok(true);
        } catch (DataAccessException e) {
            return null;
        }
    }


    private Tutorial queryUsersTutorial(Tutorial tutorial)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM tutorials WHERE tutorial_name = '" +
                        tutorial.getTutorialName() + "' AND author_id = '" + tutorial.getAuthorId() + "'",
                new TutorialRowMapper());
    }
}
