import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === 'ed3695e981ec39df57ff34d929fdfc21d8be40118d42292356792026f67a04a9') {
    pending.push(import('./chunks/chunk-93b9c3d7b1e12e73f96d7af246e2efda95a85a4d1e1325c2d166b6f51708847c.js'));
  }
  if (key === '19cad663b598fb7a34e61335cfa417106bec6d5fa220dd0cfef5b6f695d55808') {
    pending.push(import('./chunks/chunk-fe802cfdded82d1f16e7c9f268fb081478edb46a5e034a6b92218b9b0299d38c.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;
window.Vaadin.Flow.resetFocus = () => {
 let ae=document.activeElement;
 while(ae&&ae.shadowRoot) ae = ae.shadowRoot.activeElement;
 return !ae || ae.blur() || ae.focus() || true;
}