<%
    ui.decorateWith("kenyaemr", "standardPage", [patient: currentPatient, layout: "sidebar"])
    def menuItems = [
            [label: "Back to home", iconProvider: "kenyaui", icon: "buttons/back.png", label: "Back to Client home", href: ui.pageLink("kenyaemr", "clinician/clinicianViewPatient", [patient: currentPatient, patientId: currentPatient.patientId])]
    ]
%>
<style>
div.grid {
    display: block;
}

div.grid div {
    float: left;
    height: 30px;
}

div.column-one {
    width: 180px;
}

div.column-two {
    width: 80px;
}

div.column-three {
    width: 160px;
}
.col-header {
    font-weight: bold;
    font-size: 14px;
}

div.section-title {
    color: black;
    font-weight: bold;
    display: block;
    width: 550px;
    float: left;
    font-size: 16px;
}
</style>

<div class="ke-page-sidebar">
    <div class="ke-panel-frame">
        ${ui.includeFragment("kenyaui", "widget/panelMenu", [heading: "Navigation", items: menuItems])}
    </div>
</div>

<div class="ke-page-content">

    <div class="ke-panel-frame">
        <div class="ke-panel-heading">Allergies and Chronic Illnesses</div>

        <div class="ke-panel-content">
            <div class="section-title"></div>

            <div class="clear"></div>
            <% if (allergies) { %>
            <div class="grid">

                <div class="column-one col-header">Allergen</div>

                <div class="column-two col-header">Reaction</div>

                <div class="column-three col-header">Severity</div>


            </div>

            <div class="clear"></div>


            <% allergies.each { rel -> %>

            <div class="ke-stack-item ke-navigable">
                <div class="grid">

                    <div class="column-one">${rel.allergen}</div>

                    <div class="column-two">${rel.reactions}</div>

                    <div class="column-three">${rel.severity}</div>

                </div>

                <div class="clear"></div>

            </div>
            <% }
            } else { %>
            No Allergy found
            <% } %>
        </div>

        <div class="clear"></div>

    </div>

    <div align="center">

        <button type="button"
                onclick="ui.navigate('${ ui.pageLink("kenyaemrallergyandchronicillnessui", "addUpdateAllergiesAndChronicIllnessesForm", [ patientId: patient.id,  returnUrl: ui.thisUrl() ])}')">
            <img src="${ui.resourceLink("kenyaui", "images/glyphs/add.png")}"/>Add Allergy
        </button>

    </div>

</div>