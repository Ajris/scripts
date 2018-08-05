package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "script")
@Data
@NoArgsConstructor
public class Script {
    @Id
    private String id;

    @Indexed(unique = true)
    private String title;

    private String description;

    private String text;
}
