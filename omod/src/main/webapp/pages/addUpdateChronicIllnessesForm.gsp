<%
    ui.decorateWith("kenyaemr", "standardPage", [patient: currentPatient, layout: "sidebar"])
    def menuItems = [
            [label: "Back to home", iconProvider: "kenyaui", icon: "buttons/back.png", label: "Back to home", href: ui.pageLink("kenyaemrallergyandchronicillnessui", "chronicIllnessesList", [patient: currentPatient, patientId: currentPatient.id])]
    ]
%>

<div class="ke-page-sidebar">
    <div class="ke-panel-frame">
        ${ui.includeFragment("kenyaui", "widget/panelMenu", [heading: "Navigation", items: menuItems])}
    </div>
</div>
<div class="ke-page-content">
    ${ ui.includeFragment("kenyaemrallergyandchronicillnessui", "chronicIllnessForm", [ patientId: currentPatient.id, illnessId: chronicIllness != null? chronicIllness.id : null, returnUrl: ui.pageLink("kenyaemrallergyandchronicillnessui", "chronicIllnessesList", [patientId: currentPatient.id]) ]) }
   </div>