package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
// import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// import javax.validation.constraints.NotNull;

@Document(collection = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    // @Schema(description = "UserId in the database")
    @JsonProperty("id")
    private ObjectId id;
    // @NotNull
    private String username;
    // @NotNull
    private String password;
}
