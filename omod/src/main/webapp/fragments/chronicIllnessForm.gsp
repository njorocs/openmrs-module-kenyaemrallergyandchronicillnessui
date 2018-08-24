<%
    ui.decorateWith("kenyaui", "panel", [heading: (command.original ? "Edit" : "Add") + " Allergies", frameOnly: true])

    def onSetDate = [
            [


                    [object: command, property: "onsetDate", label: "Illness Start Date"]


            ]
    ]
%>


<form id="edit-chronicillness-form" method="post"
      action="${ui.actionLink("kenyaemrallergyandchronicillnessui", "chronicIllnessForm", "saveChronicIllness")}">
    <input type="hidden" name="patientId" value="${patient.patientId}"/>
    <% if (command.original) { %>
    <input type="hidden" name="id" value="${command.original.illnessId}"/>
    <% } %>

    <div class="ke-panel-content">

        <div class="ke-form-globalerrors" style="display: none"></div>

        <div class="ke-form-instructions">
            <strong>*</strong> indicates a required field
        </div>

        <fieldset class="patient-details">
            <legend>Known Chronic Illnesses</legend>

            <table id="tbl-known-chronicillnesses">
                <tr>
                    <td colspan="2">Record any known Chronic Illnesses<br/>
                        <table>
                            <tr>
                                <th>Chronic Illness</th>
                                <th>Onset Date</th>

                            </tr>
                                    <tr>
                                        <td>
                                            <select name="illnessConceptId" id="illnessConceptId">
                                                <option></option>
                                                <% chronicIllnessesOptions.each { %>
                                                <option ${(command.chronicIllness == null) ? "" : it.value == command.chronicIllness ? "selected" : ""}
                                                        value="${it.value}">${it.label}</option>
                                                <% } %>
                                            </select>
                                        </td>
                                        <td style="width: 270px">
                                            ${ui.includeFragment("kenyaui", "widget/field", [object: command, property: "onsetDate"])}
                                        </td>

                                    </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </fieldset>

        <div class="ke-panel-footer">
            <button type="submit">
                <img src="${ui.resourceLink("kenyaui", "images/glyphs/ok.png")}"/> ${command.original ? "Save Changes" : "Save Allergy"}
            </button>
            <% if (config.returnUrl) { %>
            <button type="button" class="cancel-button"><img
                    src="${ui.resourceLink("kenyaui", "images/glyphs/cancel.png")}"/> Cancel</button>
            <% } %>

        </div>
    </div>
</form>