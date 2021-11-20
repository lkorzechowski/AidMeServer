package com.orzechowski.aidme;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orzechowski.aidme.entities.document.DocumentRowMapper;
import com.orzechowski.aidme.entities.helper.Helper;
import com.orzechowski.aidme.entities.helper.HelperFullMapper;
import com.orzechowski.aidme.entities.instructionset.InstructionSet;
import com.orzechowski.aidme.entities.keyword.Keyword;
import com.orzechowski.aidme.entities.keyword.KeywordRowMapper;
import com.orzechowski.aidme.entities.multimedia.Multimedia;
import com.orzechowski.aidme.entities.multimedia.MultimediaRowMapper;
import com.orzechowski.aidme.entities.tag.Tag;
import com.orzechowski.aidme.entities.tag.TagRowMapper;
import com.orzechowski.aidme.entities.tutorial.Tutorial;
import com.orzechowski.aidme.entities.tutorial.TutorialRowMapper;
import com.orzechowski.aidme.entities.tutoriallink.TutorialLink;
import com.orzechowski.aidme.entities.tutorialsound.TutorialSound;
import com.orzechowski.aidme.entities.tutorialsound.TutorialSoundRowMapper;
import com.orzechowski.aidme.entities.tutorialtag.TutorialTag;
import com.orzechowski.aidme.entities.version.Version;
import com.orzechowski.aidme.entities.version.VersionRowMapper;
import com.orzechowski.aidme.entities.versioninstruction.VersionInstruction;
import com.orzechowski.aidme.entities.versionmultimedia.VersionMultimedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.WritableResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class PostRestController
{
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private final ApplicationContext context;
    private final static String pathBase = "gs://aidme/";
    private final Decoder decoder = new Decoder();
    private final Gson gson = new Gson();

    public PostRestController(JdbcTemplate jdbcTemplate, ApplicationContext context)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.context = context;
    }

    //email pozostaje zakodowany aby uniknac podwojnej kropki
    @PostMapping("/userdocumentuploadimage/{email}")
    public String uploadDocument(@RequestParam("file") MultipartFile file, @PathVariable String email)
    {
        String path = pathBase + "docs/" + email + ".jpeg";
        WritableResource newResource = (WritableResource) context.getResource(path);
        try {
            OutputStream output = newResource.getOutputStream();
            output.write(file.getBytes());
            output.close();
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/upload/image/{name}")
    public String uploadImage(@RequestParam("file") MultipartFile file, @PathVariable String name)
    {
        String path = pathBase + "images/" + name + ".jpeg";
        WritableResource newResource = (WritableResource) context.getResource(path);
        try {
            OutputStream output = newResource.getOutputStream();
            output.write(file.getBytes());
            output.close();
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/upload/narration/{name}")
    public String uploadNarration(@RequestParam("file") MultipartFile file, @PathVariable String name)
    {
        String path = pathBase + "narrations/" + name + ".mp3";
        WritableResource newResource = (WritableResource) context.getResource(path);
        try {
            OutputStream output = newResource.getOutputStream();
            output.write(file.getBytes());
            output.close();
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/upload/video/{name}")
    public String uploadVideo(@RequestParam("file") MultipartFile file, @PathVariable String name)
    {
        String path = pathBase + "vids/" + name + ".mp4";
        WritableResource newResource = (WritableResource) context.getResource(path);
        try {
            OutputStream output = newResource.getOutputStream();
            output.write(file.getBytes());
            output.close();
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/upload/sound/{name}")
    public String uploadSound(@RequestParam("file") MultipartFile file, @PathVariable String name)
    {
        String path = pathBase + "sounds/" + name + ".mp3";
        WritableResource newResource = (WritableResource) context.getResource(path);
        try {
            OutputStream output = newResource.getOutputStream();
            output.write(file.getBytes());
            output.close();
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/userdocumentuploadinfo/{email}")
    public ResponseEntity<String> uploadInfo(@PathVariable("email") String email, @RequestBody String description)
    {
        try {
            Helper helper = jdbcTemplate.queryForObject("SELECT * FROM helper WHERE helper_email = '" +
                    decoder.decodeEmail(email) + "'", new HelperFullMapper());
            assert helper != null;
            jdbcTemplate.execute("INSERT INTO document VALUES(default, '" + email + "', '" +
                    description + "', " + helper.getHelperId() + ")");
            return ResponseEntity.ok(String.valueOf(Objects.requireNonNull(jdbcTemplate.queryForObject(
                    "SELECT * FROM document WHERE document_file_name = '" + email + "'",
                    new DocumentRowMapper())).getDocumentId()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/create/tutorial/{email}")
    public String createTutorial(@PathVariable String email, @RequestBody String request)
    {
        Tutorial tutorial = gson.fromJson(request, Tutorial.class);
        Helper helper = jdbcTemplate.queryForObject("SELECT * FROM helper WHERE helper_email = '" +
                        decoder.decodeEmail(email) + "'", new HelperFullMapper());
        if(helper!=null) {
            try {
                jdbcTemplate.execute("INSERT INTO tutorial VALUES(default, '" + tutorial.getTutorialName() + "', " +
                        helper.getHelperId() + ", '" + tutorial.getMiniatureName() + "', 0, 'f'");
                return String.valueOf(Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT * FROM " +
                                "tutorials WHERE tutorial_name = '" + tutorial.getTutorialName() + "' AND author_id = " +
                                helper.getHelperId(), new TutorialRowMapper())).getTutorialId());
            } catch (DataAccessException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }

    @PostMapping("/create/instructions")
    public String createInstructions(@RequestBody String request)
    {
        List<InstructionSet> instructions = gson.fromJson(request, new TypeToken<ArrayList<InstructionSet>>(){}
                .getType());
            try {
                for(InstructionSet instruction : instructions) {
                    jdbcTemplate.execute("INSERT INTO instruction_set VALUES(default, '" + instruction.getTitle() +
                            "', '" + instruction.getInstructions() + "', " + instruction.getTime() + ", " +
                            instruction.getTutorialId() + ", " + instruction.getPosition() + ", '" +
                            instruction.getNarrationFile() + "')");
                }
                return "ok";
            } catch (DataAccessException e) {
                e.printStackTrace();
                return null;
            }
    }

    @PostMapping("/create/versions")
    public String createVersions(@RequestBody String request)
    {
        List<Version> versions = gson.fromJson(request, new TypeToken<ArrayList<Version>>(){}.getType());
        if(versions!=null && !versions.isEmpty()) {
            try {
                for(Version version : versions) {
                    jdbcTemplate.execute("INSERT INTO version VALUES(default, '" + version.getText() + "', " +
                            version.getTutorialId() + ", '" + version.isDelayGlobalSound() + "', '" +
                            version.isHasChildren() + "', '" + version.isHasParent() + "', " +
                            version.getParentVersionId() + ")");
                }
                return gson.toJson(jdbcTemplate.query("SELECT * FROM version WHERE tutorial_id = " +
                        versions.get(0).getTutorialId(), new VersionRowMapper()));
            } catch (DataAccessException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }

    @PostMapping("/create/multimedia")
    public String createMultimedia(@RequestBody String request)
    {
        List<Multimedia> multimedia = gson.fromJson(request, new TypeToken<ArrayList<Multimedia>>(){}.getType());
        if(multimedia!=null && !multimedia.isEmpty()) {
            try {
                for(Multimedia multi : multimedia) {
                    jdbcTemplate.execute("INSERT INTO multimedia VALUES(default, " + multi.getTutorialId() +
                            ", " + multi.getDisplayTime() + ", '" + multi.isType() + "', '" + multi.getFileName() +
                            "', '" + multi.isLoop() + "', " + multi.getPosition() + ")");
                }
                return gson.toJson(jdbcTemplate.query("SELECT * FROM multimedia WHERE tutorial_id = " +
                        multimedia.get(0).getTutorialId(), new MultimediaRowMapper()));
            } catch (DataAccessException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }

    @PostMapping("/create/sounds")
    public String createSounds(@RequestBody String request)
    {
        List<TutorialSound> sounds = gson.fromJson(request, new TypeToken<ArrayList<TutorialSound>>(){}.getType());
        if(sounds!=null && !sounds.isEmpty()) {
            try {
                for(TutorialSound sound: sounds) {
                    jdbcTemplate.execute("INSERT INTO tutorial_sound VALUES(default, " + sound.getSoundStart() +
                            ", '" + sound.isSoundLoop() + "', " + sound.getInterval() + ", " + sound.getTutorialId() +
                            ", '" + sound.getFileName() + "')");
                }
                return gson.toJson(jdbcTemplate.query("SELECT * FROM tutorial_sound WHERE sound_id = " +
                        sounds.get(0).getTutorialId(), new TutorialSoundRowMapper()));
            } catch (DataAccessException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }

    @PostMapping("/create/versioninstructions")
    public String createVersionInstructions(@RequestBody String request)
    {
        List<VersionInstruction> versionInstructions = gson.fromJson(request,
                new TypeToken<ArrayList<VersionInstruction>>(){}.getType());
        if(versionInstructions!=null && !versionInstructions.isEmpty()) {
            try {
                for(VersionInstruction versionInstruction : versionInstructions) {
                    jdbcTemplate.execute("INSERT INTO version_instruction VALUES(default, " +
                            versionInstruction.getVersionId() + ", " + versionInstruction.getInstructionPosition() +
                            ")");
                }
                return "ok";
            } catch (DataAccessException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }

    @PostMapping("/create/tutoriallinks")
    public String createTutorialLinks(@RequestBody String request)
    {
        List<TutorialLink> tutorialLinks = gson.fromJson(request, new TypeToken<ArrayList<TutorialLink>>(){}.getType());
        if(tutorialLinks!=null && !tutorialLinks.isEmpty()) {
            try {
                for(TutorialLink tutorialLink : tutorialLinks) {
                    jdbcTemplate.execute("INSERT INTO tutorial_link VALUES(default, " +
                            tutorialLink.getTutorialId() + ", " + tutorialLink.getOriginId() + ", " +
                            tutorialLink.getInstructionNumber() + ")");
                }
                return "ok";
            } catch (DataAccessException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }

    @PostMapping("/create/tutorialtags/{name}/{level}")
    public String createTutorialTags(@RequestBody String request, @PathVariable String name, @PathVariable int level)
    {
        List<TutorialTag> tutorialTags = gson.fromJson(request, new TypeToken<ArrayList<TutorialTag>>(){}.getType());
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
                    return "ok";
                } else return null;
            } catch (DataAccessException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }

    @PostMapping("/create/versionmultimedia")
    public String createVersionMultimedia(@RequestBody String request)
    {
        List<VersionMultimedia> versionMultimedia = gson.fromJson(request,
                new TypeToken<ArrayList<VersionMultimedia>>(){}.getType());
        if(versionMultimedia!=null && !versionMultimedia.isEmpty()) {
            try {
                for(VersionMultimedia vM: versionMultimedia) {
                    jdbcTemplate.execute("INSERT INTO version_multimedia VALUES(default, " + vM.getMultimediaId() +
                            ", " + vM.getVersionId() + ")");
                }
                return "ok";
            } catch (DataAccessException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }

    @PostMapping("/create/keywords/{uniqueId}")
    public String createKeywords(@RequestBody String request, @PathVariable Long uniqueId)
    {
        List<Keyword> keywords = gson.fromJson(request, new TypeToken<ArrayList<Keyword>>(){}.getType());
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
                return "ok";
            } catch (DataAccessException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
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
            e.printStackTrace();
            return null;
        }
    }
}
