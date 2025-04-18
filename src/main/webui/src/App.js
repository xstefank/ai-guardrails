import axios from 'axios';
import {useState} from "react";
import TextareaAutosize from 'react-textarea-autosize';
import {Commet} from "react-loading-indicators";


function App() {

  const [prompt, setPrompt] = useState('');
  const [inputGuardrails, setInputGuardrails] = useState('-');

  const loading = document.getElementById("loading-box");
  const llmOutputWrapper = document.getElementById("llm-output-wrapper");
  const llmOutput = document.getElementById("llm-output");


  let submit = async (e) => {
    if (e != null) e.preventDefault();

    loading.style.display = "flex";
    llmOutputWrapper.style.display = "none";
    try {
      await axios.post("http://localhost:8124/chat", {
        "prompt": prompt, "inputGuardrails": inputGuardrails
      }).then(value => {
        loading.style.display = "none";
        llmOutputWrapper.style.display = "block";
        llmOutput.style.color = "black";
        llmOutput.value = value.data;
      });
    } catch (e) {
      console.log(e);
      llmOutput.value = e.response.data;
      llmOutput.style.color = "red";
      loading.style.display = "none";
      llmOutputWrapper.style.display = "block";
    }
  }

  let onEnterPress = (e) => {
    if (e.keyCode === 13 && e.ctrlKey === true) {
      submit();
    }
  }

  return (<div className="main-container">
    <h2 className="llm-title">AI Guardrails</h2>

    <form action="#" className="llm-form" onSubmit={submit} id="llmForm">
      <div className="input-wrapper">

        <label>Input guardrails: </label><select name="inputGuardrails" value={inputGuardrails} onChange={(e) => {
        setInputGuardrails(e.target.value)
      }} className="hyperparam" list="model-list">
        <option value="-">-</option>
        <option value="failure-json">failure-json</option>
        <option value="failure-json-failure-generate">failure-json-failure-generate</option>
        <option value="fatal-generate-failure-json">fatal-generate-failure-json</option>
        <option value="failure-json-fatal-generate">failure-json-fatal-generate</option>
      </select>
        <TextareaAutosize onChange={(e) => {
          setPrompt(e.target.value)
        }} className="llm-input" cols="30" rows="10" placeholder="Enter your prompt here..." onKeyDown={onEnterPress}/>
      </div>

      <input type="submit" className="llm-button" value="Send"/>
    </form>

    <div className="loading-box" id="loading-box">
      <Commet color={["#32cd32", "#327fcd", "#cd32cd", "#cd8032"]}/>
    </div>

    <div className="output-wrapper" id="llm-output-wrapper">
      <textarea id="llm-output" name="output" cols="30" rows="10" className="llm-output" placeholder="Output will be shown here..." required/>
    </div>

  </div>)
}

export default App