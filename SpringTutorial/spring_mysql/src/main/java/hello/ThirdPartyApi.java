package hello;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by spider on 12/28/17.
 */

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@Getter
@Setter
@Table(name = "thirdparty_api")
public class ThirdPartyApi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = true, length = 1000)
    private String name;
    @Column(name = "url", nullable = true, length = 1000)
    private String url;
    @Column(name = "token", nullable = true, length = 1000)
    private String token;
    @Column(name = "refresh_token", nullable = true, length = 1000)
    private String refresh_token;
    @Column(name = "username", nullable = true, length = 1000)
    private String username;
    @Column(name = "password", nullable = true, length = 1000)
    private String password;
    @Column(name = "start_date", nullable = true)
    private Date start_date;
    @Column(name = "end_date", nullable = true)
    private Date end_date;
    @Column(name = "description", nullable = true, length = 1000)
    private String description;
    @Column(name = "base_id", nullable = true)
    private int base_id;
    @Column(name = "cron", nullable = true, length = 1000)
    private String cron;
    @Column(name = "auto_upload", nullable = true)
    private boolean auto_upload;
}
