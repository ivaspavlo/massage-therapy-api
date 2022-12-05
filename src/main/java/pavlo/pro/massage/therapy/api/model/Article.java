package pavlo.pro.massage.therapy.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "article")
public class Article {

}
