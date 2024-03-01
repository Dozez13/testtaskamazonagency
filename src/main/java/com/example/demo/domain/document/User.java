package com.example.demo.domain.document;

import com.example.demo.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;
    @Indexed(unique=true)
    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private UserRole userRole;
}
