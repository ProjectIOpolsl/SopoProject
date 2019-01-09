package com.grupa1.SopoProject.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Michal on 05.12.2018
 */

@MappedSuperclass
@Getter
@Setter
public class AuditItem {

    @Column(name = "auditRU")
    protected Long auditRU; // Remove User

    @Column(name = "auditMU")
    private Long auditMU; // Modification User

    @Column(name = "auditCU", nullable = false)
    private Long auditCU = 1L; // Creation user

    @Column(name = "auditMD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditMD; // Modifacation Date

    @Column(name = "auditRD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditRD; // Remove date

    @Column(name = "auditCD", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditCD;

    public AuditItem(){
        this.auditCD = new Date();
    }

}
