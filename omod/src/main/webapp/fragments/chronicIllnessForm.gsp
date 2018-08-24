<%
    ui.decorateWith("kenyaui", "panel", [heading: (command.original ? "Edit" : "Add") + " Allergies", frameOnly: true])

%>


<form id="edit-allergies-and-chronicillness-form" method="post"
      action="${ui.actionLink("kenyaemrallergyandchronicillnessui", "allergyAndChronicIllnessForm", "saveAllergy")}">
    <input type="hidden" name="patientId" value="${patient.patientId}"/>
    <% if (command.original) { %>
    <input type="hidden" name="id" value="${command.original.allergyId}"/>
    <% } %>

    <div class="ke-panel-content">

        <div class="ke-form-globalerrors" style="display: none"></div>

        <div class="ke-form-instructions">
            <strong>*</strong> indicates a required field
        </div>

        <fieldset class="patient-details">
            <legend>Known Allergies</legend>

            <table id="tbl-known-allergies">
                <tr>
                    <td colspan="2">Record any known allergies<br/>
                        <table>
                            <tr>
                                <th>Causative agent</th>
                                <th>Reaction</th>
                                <th>Severity</th>

                            </tr>
                                    <tr>
                                        <td>
                                            <select name="allergenConceptId" id="allergen">
                                                <option></option>
                                                <% allergenOptions.each { %>
                                                <option ${(command.allergy == null) ? "" : it.value == command.allergy ? "selected" : ""}
                                                        value="${it.value}">${it.label}</option>
                                                <% } %>
                                            </select>
                                        </td>
                                        <td>
                                            <select name="reaction" id="reaction">
                                                <option></option>
                                                <% reactionOptions.each { %>
                                                <option ${(command.reaction == null) ? "" : it.value == command.reaction ? "selected" : ""}
                                                        value="${it.value}">${it.label}</option>
                                                <% } %>
                                            </select>
                                        </td>
                                        <td>
                                            <select name="severity" id="severity">
                                                <option></option>
                                                <% severityOptions.each { %>
                                                <option ${(command.severity == null) ? "" : it.value == command.severity ? "selected" : ""}
                                                        value="${it.value}">${it.label}</option>
                                                <% } %>
                                            </select>
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