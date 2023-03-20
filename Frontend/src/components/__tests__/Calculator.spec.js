
import { mount } from "@vue/test-utils";
import Calculator from "../Calculator.vue";
import { describe, it, expect, beforeEach } from "vitest";


describe("Calculator", () => {
    let wrapper;
  
    beforeEach(() => {
      wrapper = mount(Calculator);
      wrapper.find("#buttonAC").trigger("click");
    });
  
    it("displays numbers when they are clicked", async () => {
    let value = "";
      for (let i = 0; i < 10; i++) {
        const button = wrapper.find(`#button${i}`);
        value += i;
        await button.trigger("click");
        expect(wrapper.vm.$data.inputValue).toBe(value);
      }
    });
  
    it("performs addition correctly", async () => {
      wrapper.find("#button1").trigger("click");
      wrapper.find("#buttonAdd").trigger("click");
      wrapper.find("#button2").trigger("click");
      await wrapper.find("#buttonEquals").trigger("click");
      expect(wrapper.vm.$data.result).toBe(3);
    });
  
    it("performs subtraction correctly", async () => {
      wrapper.find("#button5").trigger("click");
      wrapper.find("#buttonDiff").trigger("click");
      wrapper.find("#button3").trigger("click");
      await wrapper.find("#buttonEquals").trigger("click");
      expect(wrapper.vm.$data.result).toBe(2);
    });
  
    it("performs multiplication correctly", async () => {
      wrapper.find("#button4").trigger("click");
      wrapper.find("#buttonMulti").trigger("click");
      wrapper.find("#button3").trigger("click");
      await wrapper.find("#buttonEquals").trigger("click");
      expect(wrapper.vm.$data.result).toBe(12);
    });
  
    it("performs division correctly", async () => {
      wrapper.find("#button8").trigger("click");
      wrapper.find("#buttonDiv").trigger("click");
      wrapper.find("#button2").trigger("click");
      await wrapper.find("#buttonEquals").trigger("click");
      expect(wrapper.vm.$data.result).toBe(4);
    });
  });